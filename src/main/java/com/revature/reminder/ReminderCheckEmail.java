package com.revature.reminder;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
		System.out.println("lets se if it checks every minute "+ i++);;
	}
}
