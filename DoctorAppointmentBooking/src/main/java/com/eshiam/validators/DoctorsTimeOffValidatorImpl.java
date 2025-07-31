package com.eshiam.validators;

import org.joda.time.DateTime;

import com.eshiam.domains.DoctorsTimeOff;
import com.eshiam.interfaces.DoctorsTimeOffValidator;
import com.eshiam.serviceDTOs.ServiceDTO;

public class DoctorsTimeOffValidatorImpl implements DoctorsTimeOffValidator {

	@Override
	public void validateDoctorsTimeOff(ServiceDTO<DoctorsTimeOff> dto) {
		DoctorsTimeOff doctorsTimeOff = new DoctorsTimeOff();
		if (doctorsTimeOff != null) {
			validateStartDate(dto);
			validateEndDate(dto);
		}

	}

	private void validateEndDate(ServiceDTO<DoctorsTimeOff> dto) {
		DoctorsTimeOff doctorsTimeOff = dto.getDataObject();
		DateTime value = doctorsTimeOff.getEndDate();
		if (value == null || value.compareTo(new DateTime()) >= 0) {
			dto.addApplicationMessage("DT002", "please enter valid end date", 5, "endDate", doctorsTimeOff.getEndDate());
		}
	}

	private void validateStartDate(ServiceDTO<DoctorsTimeOff> dto) {
		DoctorsTimeOff doctorsTimeOff = dto.getDataObject();
		DateTime value = doctorsTimeOff.getStartDate();
		if (value == null || value.compareTo(new DateTime()) >= 0) {
			dto.addApplicationMessage("DT001", "please enter valid start date", 5, "startDate", doctorsTimeOff.getStartDate());
		}

	}

}
