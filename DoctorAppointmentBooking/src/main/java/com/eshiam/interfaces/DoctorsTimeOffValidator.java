package com.eshiam.interfaces;

import com.eshiam.domains.DoctorsTimeOff;
import com.eshiam.serviceDTOs.ServiceDTO;

public interface DoctorsTimeOffValidator {
	public void validateDoctorsTimeOff(ServiceDTO<DoctorsTimeOff> dto);

}
