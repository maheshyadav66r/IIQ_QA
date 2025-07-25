package com.eshiam.domains;

import java.io.Serializable;

public class AppointmentBooking implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -734261693537938934L;
	private int id;
	private String patientName;
	private String bookingStartTime;
	private String bookingEndTime;
	private int appointmentFee;
	private String appointmentStatus; // cancel or reschedule or booked
	private String rescheduleStartTime;
	private String rescheduleEndTime;
	private String action;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getBookingStartTime() {
		return bookingStartTime;
	}

	public void setBookingStartTime(String bookingStartTime) {
		this.bookingStartTime = bookingStartTime;
	}

	public String getBookingEndTime() {
		return bookingEndTime;
	}

	public void setBookingEndTime(String bookingEndTime) {
		this.bookingEndTime = bookingEndTime;
	}

	public int getAppointmentFee() {
		return appointmentFee;
	}

	public void setAppointmentFee(int appointmentFee) {
		this.appointmentFee = appointmentFee;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public String getRescheduleStartTime() {
		return rescheduleStartTime;
	}

	public void setRescheduleStartTime(String rescheduleStartTime) {
		this.rescheduleStartTime = rescheduleStartTime;
	}

	public String getRescheduleEndTime() {
		return rescheduleEndTime;
	}

	public void setRescheduleEndTime(String rescheduleEndTime) {
		this.rescheduleEndTime = rescheduleEndTime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
