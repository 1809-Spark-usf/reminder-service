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
import com.revature.model.ReservationEmail;
import com.revature.service.EmailRepositoryService;

/**
 * The Class ReminderCheckEmail.
 * Every minute between the time
 * of 8:00am-5:00pm/mon-fri it will check 
 * the database for any reservation
 * reminders that need to be sent.
 * 
 *  @author 1811-Java-Nick | 01/08/2019
 */
@Service
public class ReminderCheckEmail {
	
	/*change url to reminder endpoint*/
	@Value("${RMS_EMAIL_URL:http://localhost:8080/email/}")
	String emailUri;
	
	/** The reservation email repository. */
	@Autowired
	EmailRepositoryService emailRepositoryService;
	
	/** The i. */
	public Integer i = 0;
	
	/**
	 * Check for appointment.
	 * A method with a cron scheduler that
	 * for every minute starting at 8 am and 
	 * ending at 5 pm (work days including Sunday),
	 * it will check the repository for any and all 
	 * upcoming appointments. 
	 */
	@Scheduled(cron = "0 0/1 8-17 ? * 1-6")
	public void checkForAppointment() {
		
		/* goes to the repository service to get all items in the database */
		List<ReservationEmail> emails= emailRepositoryService.getAllByTime(LocalDateTime.now());
		System.out.println(emails);
		
		/* Displays how many time this has run */
		System.out.println("lets se if it checks every minute "+ i++);
		
		sendEmailToEmailService(emails);
	}
	/**
	 * send Email Object to Email microservice.
	 * This method grabs a list of reservation email 
	 * objects and sends it to the email microservice
	 * so it can become an AWS email object and be sent to 
	 * the appropriate user.
	 * 
	 * @param reservationEmail
	 */
	@HystrixCommand(fallbackMethod = "emailFallback")
	public void sendEmailToEmailService(List<ReservationEmail> reservationEmail) {
		
		Iterator<ReservationEmail> i = reservationEmail.iterator();
		
		while(i.hasNext()) {
			new RestTemplate().postForLocation(URI.create(emailUri + "sendreminder"), i.next());
			/* Add delete the object from database so it wont constantly send and email every minute
			 * make another method*/
		}
		
	}
	@SuppressWarnings("unused")
	private void emailFallback(ReservationEmail reservation) {
	}
	
}
