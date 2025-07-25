package com.eshiam.interfaces;

import com.eshiam.domains.HospitalRegistry;

public interface HospitalRegistryDAO {
	public HospitalRegistry save( HospitalRegistry  hospitalRegistry);
	public HospitalRegistry retrieve( HospitalRegistry  hospitalRegistry);
	public HospitalRegistry delete( HospitalRegistry  hospitalRegistry);

}
