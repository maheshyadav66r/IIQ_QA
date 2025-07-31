package com.eshiam.interfaces;

import com.eshiam.domains.DoctorsTimeOff;
import com.eshiam.serviceDTOs.ServiceDTO;

public interface DoctorsTimeOffService {
public ServiceDTO<DoctorsTimeOff> saveDoctorsTimeOff(DoctorsTimeOff doctorsTimeOff);
public ServiceDTO<DoctorsTimeOff> retrieveDoctorsTimeOff(DoctorsTimeOff doctorsTimeOff);
public ServiceDTO<DoctorsTimeOff> deleteDoctorsTimeOff(DoctorsTimeOff dsoctorsTimeOff);
}
