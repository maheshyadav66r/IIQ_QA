package com.eshiam.serviceDTOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ServiceDTO<T>{
	private T dataObject;

	private int statusCode;

	private String status;

	private int severity;

	private List<ApplicationMessage> messages = new ArrayList<>();

	public ServiceDTO() {
	}

	public T getDataObject() {
		return dataObject;
	}

	public void setDataObject(T dataObject) {
		this.dataObject = dataObject;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public List<ApplicationMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ApplicationMessage> messages) {
		this.messages = messages;
	}

	public void addApplicationMessage(String msgCode, String msgDescription, int severity, String fieldKey, Object fieldValue) {
		messages.add(new ApplicationMessage(msgCode, msgDescription, fieldKey, fieldValue, severity));
		if (this.severity < severity) {
			this.severity = severity;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataObject, messages, severity, status, statusCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceDTO other = (ServiceDTO) obj;
		return Objects.equals(dataObject, other.dataObject) && Objects.equals(messages, other.messages) && severity == other.severity && Objects.equals(status, other.status) && statusCode == other.statusCode;
	}

	

}
