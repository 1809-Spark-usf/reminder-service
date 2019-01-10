package com.revature.reminder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.model.ReminderEmail;
import com.revature.service.EmailRepositoryService;

/**
 * The Class ReminderCheckEmail.
 * Every minute between the time
 * of 8:00am-5:00pm/Mon-Fri it will check 
 * the database for any reservation
 * reminders that need to be sent.
 * 
 *  @author 1811-Java-Nick | 01/08/2019
 */
@Service
public class ReminderCheckEmail {
	
	/** The email uri.*/
	@Value("${RMS_EMAIL_URL:http://localhost:8080/email/}")
	String emailUri;
	
	/** The reservation email repository. */
	@Autowired
	EmailRepositoryService emailRepositoryService;
	

	
	/**
	 * Check for appointment.
	 * A method with a cron scheduler that
	 * for every minute starting at 8 am and 
	 * ending at 5:00pm (work days including Sunday),
	 * it will check the repository for any and all 
	 * upcoming appointments. 
	 */
	@Scheduled(cron = "0 0/1 8-17 ? * 1-6")
	public void checkForAppointment() {
		
		/* goes to the repository service to get all reminders that need to be sent */
		List<ReminderEmail> emails= emailRepositoryService.getAllByTime(LocalDateTime.now());
		
		/* sends the reminder object one by one to the email service */
		Iterator<ReminderEmail> i = emails.iterator();
		while(i.hasNext()) {
			sendEmailToEmailService(i.next());
		}
	}
	
	/**
	 * Send Email Object to Email microservice.
	 * This method grabs a list of reservation email 
	 * objects and sends it to the email microservice
	 * so it can become an AWS email object and be sent to 
	 * the appropriate user.
	 *
	 * @param reminderEmail the reservation email
	 */
	@HystrixCommand(fallbackMethod = "emailFallback")
	public void sendEmailToEmailService(ReminderEmail reminderEmail) {
		
			/* Creates the rest template to send the object to that URL (AKA email service) */
			new RestTemplate().postForLocation(URI.create(emailUri + "sendreminder"), reminderEmail);
			
			/* Sends the object to a delete method because the reminder has been sent */
			deleteSentEmailObject(reminderEmail);
	}
	
	/**
	 * Email fallback.
	 *
	 * @param reservation the reservation
	 */
	@SuppressWarnings("unused")
	private void emailFallback(ReminderEmail reservation) {
	}
	
	
	/**
	 * Delete sent email object.
	 * Goes to the repository service
	 * and deletes the reminder object
	 * from the database so it wont be
	 * sent again.
	 *
	 * @param reservation the reservation
	 */
	public void deleteSentEmailObject(ReminderEmail reservation) {

		emailRepositoryService.deleteReminder(reservation);
	}
	
}
