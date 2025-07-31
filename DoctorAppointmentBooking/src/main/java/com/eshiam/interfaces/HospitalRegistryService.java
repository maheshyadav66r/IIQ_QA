package com.eshiam.interfaces;

import com.eshiam.domains.HospitalRegistry;
import com.eshiam.serviceDTOs.ServiceDTO;

public interface HospitalRegistryService {
	public ServiceDTO<HospitalRegistry> saveHospitalRegistry(HospitalRegistry hospitalRegistry);

	public ServiceDTO<HospitalRegistry> retrieveHospitalRegistry(HospitalRegistry hospitalRegistry);

	public ServiceDTO<HospitalRegistry> deleteHospitalRegistry(HospitalRegistry hospitalRegistry);
}
