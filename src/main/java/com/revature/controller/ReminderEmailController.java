package com.revature.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.model.ReservationEmail;



public class ReminderEmailController {

	@PostMapping("newreminder")
	public void sendConfirmation(@RequestBody ReservationEmail reservationEmail) throws IOException {
		
		/*Sends the ReservationEmail Object to the EmailRepositoryService
		 * service that will save it to the Database*/
		
	}
}
