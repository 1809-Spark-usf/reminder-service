package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.ReservationEmail;
import com.revature.repository.ReservationEmailRepository;

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
	ReservationEmailRepository reservationEmailRepository;
	
	/**
	 * Save new email.
	 * Grabs a reservation email object from
	 * the controller to save the reservation
	 * email to the database.
	 *
	 * @param reservationEmail the reservation email object
	 */
	public void saveNewEmail(ReservationEmail reservationEmail) {
		reservationEmailRepository.save(reservationEmail);
	}
	
	/**
	 * Gets the all.
	 * Grabs all the ReservationEmail objects
	 * from the database.
	 *
	 * @return the List of ReservationEmail objects in the DataBase
	 */
	public List<ReservationEmail> getAll(){
		return reservationEmailRepository.findAll();
	}

}
