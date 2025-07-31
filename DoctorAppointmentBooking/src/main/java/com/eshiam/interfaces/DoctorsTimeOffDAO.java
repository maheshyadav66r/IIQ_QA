package com.eshiam.interfaces;

import com.eshiam.domains.DoctorsTimeOff;

public interface DoctorsTimeOffDAO {
	public DoctorsTimeOff  save(DoctorsTimeOff doctorsTimeOff);
	
	public DoctorsTimeOff  retrieve(DoctorsTimeOff doctorsTimeOff);
	
	public DoctorsTimeOff  delete(DoctorsTimeOff doctorsTimeOff);
	

}
