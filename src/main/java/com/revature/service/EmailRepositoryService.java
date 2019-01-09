package com.revature.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.ReminderEmail;
import com.revature.repository.ReminderEmailRepository;

/**
 * The Class EmailRepositoryService.
 * Service design only to talk directly 
 * to the database and run any CRUD methods
 * 
 * @author 1811-Java-Nick | 01/07/2019
 */
@Service
public class EmailRepositoryService {

	/** The reservation email repository. */
	@Autowired
	ReminderEmailRepository reminderEmailRepository;
	

	
	/**
	 * Save new email.
	 * Grabs a reservation email object from
	 * the controller to save the reservation
	 * email to the database.
	 *
	 * @param reminderEmail the reservation email object
	 */
	public void saveNewEmail(ReminderEmail reminderEmail) {
		reminderEmailRepository.save(reminderEmail);
	}
	
	/**
	 * Gets the all.
	 * Grabs all the ReservationEmail objects
	 * from the database.
	 * 
	 *
	 * @return the List of ReservationEmail objects in the DataBase
	 */
	public List<ReminderEmail> getAll(){

		return reminderEmailRepository.findAll();
	}

	/**
	 * Gets all the reservation email objects
	 * which reminder date is less or equals 
	 * date time NOW.
	 *
	 * @param localDateTime the local date time
	 * @return the list of reservation email by time
	 */
	public List<ReminderEmail> getAllByTime(LocalDateTime localDateTime) {
		
		return reminderEmailRepository.findAllByTime(localDateTime);
	}

	public void deleteReminder(ReminderEmail reminderEmail) {
		reminderEmailRepository.delete(reminderEmail);
		
	}

}
