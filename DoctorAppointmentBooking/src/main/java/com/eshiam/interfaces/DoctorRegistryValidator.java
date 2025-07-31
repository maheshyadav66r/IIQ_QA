package com.eshiam.interfaces;

import com.eshiam.domains.DoctorRegistry;
import com.eshiam.serviceDTOs.ServiceDTO;

public interface DoctorRegistryValidator {
	public void validateDoctorRegistry(ServiceDTO<DoctorRegistry> dto);

}
