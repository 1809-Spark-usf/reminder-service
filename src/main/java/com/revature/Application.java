package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The Class Application.
 * Microservice to check the database
 * every minute for potential reservations
 * that need to be sent.
 * 
 * @author 1811-Java-Nick | 01/08/2019
 */
@SpringBootApplication
@EnableScheduling
public class Application {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

