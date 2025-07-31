package com.eshiam.domains;

import java.io.Serializable;

import org.joda.time.DateTime;

public class HospitalToDoctor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3535795781382374334L;
	private int id;
	private int hospitalId;
	private int doctorId;
	private int appointmentFee;
	private int startHours;           // working hours
	private int startMinutes;
	private int endHours;
	private int endMinutes;
	private DoctorRegistry doctorRegistry = new DoctorRegistry();

	public HospitalToDoctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HospitalToDoctor( int hospitalId, int doctorId, int appointmentFee, int startHours, int startMinutes, int endHours,int endMinutes ) {
		super();
		
		this.hospitalId = hospitalId;
		this.doctorId = doctorId;
		this.appointmentFee = appointmentFee;
		this.startHours = startHours;
		this.startMinutes = startMinutes;
		this.endHours = endHours;
		this.endMinutes=endMinutes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getAppointmentFee() {
		return appointmentFee;
	}

	public void setAppointmentFee(int appointmentFee) {
		this.appointmentFee = appointmentFee;
	}

	public int getStartHours() {
		return startHours;
	}

	public void setStartHours(int startHours) {
		this.startHours = startHours;
	}

	public int getStartMinutes() {
		return startMinutes;
	}

	public void setStartMinutes(int startMinutes) {
		this.startMinutes = startMinutes;
	}

	public int getEndHours() {
		return endHours;
	}

	public void setEndHours(int endHours) {
		this.endHours = endHours;
	}

	public int getEndMinutes() {
		return endMinutes;
	}

	public void setEndMinutes(int endMinutes) {
		this.endMinutes = endMinutes;
	}

	public DoctorRegistry getDoctorRegistry() {
		return doctorRegistry;
	}

	public void setDoctorRegistry(DoctorRegistry doctorRegistry) {
		this.doctorRegistry = doctorRegistry;
	}

}
