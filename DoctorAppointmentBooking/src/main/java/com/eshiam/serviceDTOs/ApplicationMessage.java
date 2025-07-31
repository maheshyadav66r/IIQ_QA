package com.eshiam.serviceDTOs;

import java.util.Objects;

public class ApplicationMessage {

	private String code;

	private String description;

	private String fieldKey;

	private Object fieldValue;

	private int Severity;

	public ApplicationMessage() {
	}

	public ApplicationMessage(String code, String description, String fieldKey, Object fieldValue, int severity) {
		super();
		this.code = code;
		this.description = description;
		this.fieldKey = fieldKey;
		this.fieldValue = fieldValue;
		Severity = severity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFieldKey() {
		return fieldKey;
	}

	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public int getSeverity() {
		return Severity;
	}

	public void setSeverity(int severity) {
		Severity = severity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Severity, code, description, fieldKey, fieldValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationMessage other = (ApplicationMessage) obj;
		return Severity == other.Severity && Objects.equals(code, other.code) && Objects.equals(description, other.description) && Objects.equals(fieldKey, other.fieldKey) && Objects.equals(fieldValue, other.fieldValue);
	}

	@Override
	public String toString() {
		return "ApplicationMessage [code=" + code + ", description=" + description + ", fieldKey=" + fieldKey + ", fieldValue=" + fieldValue + ", Severity=" + Severity + "]";
	}

}
