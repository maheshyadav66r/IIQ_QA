package com.eshiam.interfaces;

import com.eshiam.domains.HospitalRegistry;
import com.eshiam.serviceDTOs.ServiceDTO;

public interface HospitalRegistryValidator {
	public void validateHospitalRegistry(ServiceDTO<HospitalRegistry> dto);

}
