package com.eshiam.domains;

import java.io.Serializable;

import org.joda.time.DateTime;

public class DoctorsTimeOff implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -719730439851002034L;
	private int Id;
	private DateTime startDate; // leave days or working days
	private DateTime endDate;
	private String action;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
