package com.revature.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.ReservationEmail;



/**
 * The Interface ReservationEmailRepository.
 * Connection between the business logic layer
 * and the data logic layer.
 * 
 * @author 1811-Java-Nick | 01/07/2019
 */
@Repository
public interface ReservationEmailRepository extends JpaRepository<ReservationEmail,String> {
	

	/*
	 * Finds all the ReservationEmail 
	 * from the Database 
	 */
	List<ReservationEmail> findAll();
}
