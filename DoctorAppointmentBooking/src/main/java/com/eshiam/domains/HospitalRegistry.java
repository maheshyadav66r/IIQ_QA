package com.eshiam.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class HospitalRegistry  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5282551129070302491L;
	private int id;
	private String hospitalCode;
	private String hospitalName;
	private DateTime hospitalStartDate;
	private DateTime hospitalEndDate;
	private String action;
	private List<HospitalToDoctor> hospitalTodoctorList = new ArrayList<>();
	
	private DoctorRegistry doctorRegistry=new DoctorRegistry();

	public HospitalRegistry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public DateTime getHospitalStartDate() {
		return hospitalStartDate;
	}

	public void setHospitalStartDate(DateTime hospitalStartDate) {
		this.hospitalStartDate = hospitalStartDate;
	}

	public DateTime getHospitalEndDate() {
		return hospitalEndDate;
	}

	public void setHospitalEndDate(DateTime hospitalEndDate) {
		this.hospitalEndDate = hospitalEndDate;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<HospitalToDoctor> getHospitalTodoctorList() {
		return hospitalTodoctorList;
	}

	public void setHospitalTodoctorList(List<HospitalToDoctor> hospitalTodoctorList) {
		this.hospitalTodoctorList = hospitalTodoctorList;
	}

	public DoctorRegistry getDoctorRegistry() {
		return doctorRegistry;
	}

	public void setDoctorRegistry(DoctorRegistry doctorRegistry) {
		this.doctorRegistry = doctorRegistry;
	}
	
	

}
