package com.revature.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.ReminderEmail;

/**
 * The Interface ReservationEmailRepository.
 * Connection between the business logic layer
 * and the data logic layer.
 * 
 * @author 1811-Java-Nick | 01/07/2019
 */
@Repository
public interface ReminderEmailRepository extends JpaRepository<ReminderEmail,String> {
	/** The time now. */
	static LocalDateTime timeNow = LocalDateTime.now();
	/*
	 * Finds all the ReservationEmail 
	 * from the Database.
	 * 
	 */
	List<ReminderEmail> findAll();

	/**
	 * Find all reservation email by time(now).
	 *
	 * @param timeNow the time now
	 * @return the list of reservationEmails but filtered.
	 */
	@Query(value = "SELECT r FROM ReminderEmail r WHERE r.reminder_date <= :timeNow")
	List<ReminderEmail> findAllByTime(@Param("timeNow") LocalDateTime timeNow);
}
