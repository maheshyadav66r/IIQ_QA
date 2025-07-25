package com.eshiam.interfaces;

import com.eshiam.domains.DoctorRegistry;
import com.eshiam.serviceDTOs.ServiceDTO;

public interface DoctorRegistryService {
	public ServiceDTO<DoctorRegistry> saveDoctorRegistry(DoctorRegistry doctorRegistry);

	public ServiceDTO<DoctorRegistry> retrieveDoctorRegistry(DoctorRegistry doctorRegistry);

	public ServiceDTO<DoctorRegistry> deleteDoctorRegistry(DoctorRegistry doctorRegistry);
	
	
	
	public ServiceDTO<DoctorRegistry> saveDoctorRegistryByDoctorCode(DoctorRegistry doctorRegistry);

	public ServiceDTO<DoctorRegistry> retrieveDoctorRegistryByDoctorCode(DoctorRegistry doctorRegistry);

	public ServiceDTO<DoctorRegistry> deleteDoctorRegistryByDoctorCode(DoctorRegistry doctorRegistry);

}
