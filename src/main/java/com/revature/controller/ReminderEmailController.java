package com.revature.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.model.ReminderEmail;
import com.revature.service.EmailRepositoryService;

/**
 * The Class ReminderEmailController.
 * Will save ReservationEmail objects
 * retrieved from the email microservice.
 * 
 * @author 1811-Java-Nick | 01/08/2019
 */
@Controller
public class ReminderEmailController {

	/** The email repository service. */
	@Autowired
	EmailRepositoryService emailRepositoryService;
	
	/**
	 * Send new email object.
	 * Gets the ReservationEmail Object from the 
	 * Email microservice, sets the reminder date
	 * by subtracting an hour or 24 hours to the object.
	 * Then sends the new object to the repository service
	 * to be saved in the database.
	 *
	 * @param reminderEmail the reservation email
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("newreminder")
	public void sendNewEmailObject(@RequestBody ReminderEmail reminderEmail) throws IOException {
		
		/* Sets the reminder date either an hour before or a day before*/
		reminderEmail.setReminder_date(setReminderDate(reminderEmail.getStartTime(), reminderEmail.getReminderTime()));

		/*Sends the ReservationEmail Object to the EmailRepositoryService
		 * service that will save it to the Database*/
		emailRepositoryService.saveNewEmail(reminderEmail);
	}
	/**
	 * Cancel email object.
	 * Gets the ReservationEmail Object from the 
	 * Email microservice, sets the reminder date
	 * by subtracting an hour or 24 hours to the object.
	 * (must be done to get the proper object)
	 * Then sends the new object to the repository service
	 * to be deleted from the database.
	 *
	 * @param reminderEmail the reservation email
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("cancelreminder")
	public void cancelEmailObject(@RequestBody ReminderEmail reminderEmail) throws IOException {
		
		/* Sets the reminder date either an hour before or a day before*/
		reminderEmail.setReminder_date(setReminderDate(reminderEmail.getStartTime(), reminderEmail.getReminderTime()));
		
		/*Sends the ReservationEmail Object to the EmailRepositoryService
		 * service that will save it to the Database*/
		emailRepositoryService.deleteReminder(reminderEmail);
	}
	
	/**
	 * Sets the reminder date.
	 * grabs the start time from the 
	 * reservation and the number 
	 * indicating when the reminder 
	 * should be sent.
	 *
	 * @param startTime the starting date and time of the reservation.
	 * @param reminderHour, its either one hour or twentyfour hour before start time to send a reminder.
	 * @return the the reminder date that is the start time minus one hour or twentyfour hours.
	 */
	public LocalDateTime setReminderDate(LocalDateTime startTime, int reminderHour) {
		
		if(reminderHour==1) {
			
			return startTime.minus(1,ChronoUnit.HOURS);
			
		} else {
			
			return startTime.minus(1,ChronoUnit.DAYS);
		}
		
	}


}
