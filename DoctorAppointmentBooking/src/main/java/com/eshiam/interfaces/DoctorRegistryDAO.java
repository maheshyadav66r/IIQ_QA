package com.eshiam.interfaces;

import com.eshiam.domains.DoctorRegistry;

public interface DoctorRegistryDAO {
public DoctorRegistry save(DoctorRegistry doctorRegistry); 
public DoctorRegistry retrieve(DoctorRegistry doctorRegistry); 
public DoctorRegistry delete(DoctorRegistry doctorRegistry);
public int retrieveDoctorIdByDoctorName(String doctorName); 
}
