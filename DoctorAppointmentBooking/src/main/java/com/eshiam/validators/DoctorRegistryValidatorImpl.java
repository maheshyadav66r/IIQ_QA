package com.eshiam.validators;

import java.math.BigDecimal;
import java.util.Objects;

import com.eshiam.domains.DoctorRegistry;
import com.eshiam.interfaces.DoctorRegistryValidator;
import com.eshiam.serviceDTOs.ServiceDTO;

public class DoctorRegistryValidatorImpl implements DoctorRegistryValidator{

	@Override
	public void validateDoctorRegistry(ServiceDTO<DoctorRegistry> dto) {
		DoctorRegistry doctorRegistry = dto.getDataObject();

		if (doctorRegistry != null) {
			validateDoctorName(dto);
			validateDoctorCode(dto);
			validateDoctorQualification(dto); 
			validateYearsOfExperience(dto);
			

		}
	}

	private void validateDoctorCode(ServiceDTO<DoctorRegistry> dto) {
		DoctorRegistry doctorRegistry = dto.getDataObject();
		if (isNull(doctorRegistry.getDoctorCode())) {
			dto.addApplicationMessage("DR004", "please enter doctor code", 5, "DoctorCode", doctorRegistry.getDoctorCode());
		}			
	}

	private void validateYearsOfExperience(ServiceDTO<DoctorRegistry> dto) {
		DoctorRegistry doctorRegistry = dto.getDataObject();

		if (doctorRegistry.getYearsOfExperience() <= 0) {
			dto.addApplicationMessage("DR003", "please enter valid years of experience", 5,  "yearsOfExperience", doctorRegistry.getYearsOfExperience());
		}
	}

	private void validateDoctorQualification(ServiceDTO<DoctorRegistry> dto) {
		DoctorRegistry doctorRegistry = dto.getDataObject();
		if (isNull(doctorRegistry.getDoctorQualification())) {
			dto.addApplicationMessage("DR002", "please enter doctor qualification", 5, "DoctorQualification", doctorRegistry.getDoctorQualification());
		}		
	}

	private void validateDoctorName(ServiceDTO<DoctorRegistry> dto) {

		DoctorRegistry doctorRegistry = dto.getDataObject();
		if (isNull(doctorRegistry.getDoctorName())) {
			dto.addApplicationMessage("DR001", "please enter doctor name", 5, "DoctorName", doctorRegistry.getDoctorName());
		}
	}

	private boolean isNull(String value) {
		return value == null || value.trim().length() == 0;

	}

}
