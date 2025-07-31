package com.eshiam.domains;

import java.io.Serializable;

public class DoctorRegistry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6324528597214631473L;
	private int id;
	private String doctorCode;

	private String doctorName;
	private String doctorQualification;
	private int yearsOfExperience;
	private String action;

	public DoctorRegistry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorRegistry(int id, String doctorCode, String doctorName, String doctorQualification, int yearsOfExperience) {
		super();
		this.id = id;
		this.doctorCode = doctorCode;
		this.doctorName = doctorName;
		this.doctorQualification = doctorQualification;
		this.yearsOfExperience = yearsOfExperience;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public String getDoctorName() {          // (mbbs,md,SuperSpeciality)dropdown
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorQualification() {
		return doctorQualification;
	}

	public void setDoctorQualification(String doctorQualification) {
		this.doctorQualification = doctorQualification;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
