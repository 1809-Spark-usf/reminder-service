package com.revature.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The ReservationEmail Class
 * Used to transfer information needed
 * by the RMSEmailService in an object.
 * 
 * @author Austin D. | 1811-Java-Nick 1/3/19 
 *
 */
@Entity
@Table(name="ReservationEmail")
public class ReminderEmail {
	
	/**The id of the reservation for reminder purposes*/
	@Id
	@Column(name="reservationId")
	private int reservationId;
	
	/**The email of the user making the reservation*/
	@Column(name="email")
	private String email;
	
	/**The start time of the reservation*/
	@Column(name="startTime")
	private LocalDateTime startTime;
	
	/**The end time of the reservation*/
	@Column(name="endTime")
	private LocalDateTime endTime;
	
	/**The building name the reservation is taking place in*/
	@Column(name="buildingName")
	private String buildingName;
	
	/**The resource name associated with the building*/
	@Column(name="resourceName")
	private String resourceName;
	
	/**The time before the startTime that the user wants a reminder*/
	@Column(name="reminderTime")
	private int reminderTime;
	
	/**The start time of the reservation*/
	@Column(name="reminderDate")
	private LocalDateTime reminderDate;

	public LocalDateTime getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(LocalDateTime reminderDate) {
		this.reminderDate = reminderDate;
	}

	public int getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(int reminderTime) {
		this.reminderTime = reminderTime;
	}

	public ReminderEmail() {
		super();
	}
	


	@Override
	public String toString() {
		return "ReservationEmail [reservationId=" + reservationId + ", email=" + email + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", buildingName=" + buildingName + ", resourceName=" + resourceName
				+ ", reminderTime=" + reminderTime + ", reminderDate=" + reminderDate + "]";
	}

	public ReminderEmail(int reservationId, String email, LocalDateTime startTime, LocalDateTime endTime,
			String buildingName, String resourceName, int reminderTime) {
		super();
		this.reservationId = reservationId;
		this.email = email;
		this.startTime = startTime;
		this.endTime = endTime;
		this.buildingName = buildingName;
		this.resourceName = resourceName;
		this.reminderTime = reminderTime;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}


}
