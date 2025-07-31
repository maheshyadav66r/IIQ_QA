package com.eshiam.servicelayers;

import com.eshiam.dao.DoctorRegistryDAOImpl;
import com.eshiam.domains.DoctorRegistry;
import com.eshiam.domains.HospitalRegistry;
import com.eshiam.interfaces.DoctorRegistryDAO;
import com.eshiam.interfaces.DoctorRegistryService;
import com.eshiam.interfaces.DoctorRegistryValidator;
import com.eshiam.serviceDTOs.ServiceDTO;
import com.eshiam.validators.DoctorRegistryValidatorImpl;

public class DoctorRegistryServiceImpl implements DoctorRegistryService {
	private DoctorRegistryValidator validator = new DoctorRegistryValidatorImpl();
	private DoctorRegistryDAO dao = new DoctorRegistryDAOImpl();
	 private DoctorRegistryDAO ddao = new DoctorRegistryDAOImpl();

	public static void main(String[] args) {
		DoctorRegistryServiceImpl doctorRegistryServiceImpl = new DoctorRegistryServiceImpl();
		doctorRegistryServiceImpl.saveDoctorRegistry(getDoctorRegistryObject());

		DoctorRegistryDAOImpl doctorRegistryDAOImpl = new DoctorRegistryDAOImpl();
		DoctorRegistry dbDoctorRegistry = doctorRegistryDAOImpl.save(getDoctorRegistryObject());
		System.out.println("created successfully....");

	}

	private static DoctorRegistry getDoctorRegistryObject() {
		DoctorRegistry doctorRegistry = new DoctorRegistry();

		doctorRegistry.setId(0);
		doctorRegistry.setDoctorCode("DR001");
		doctorRegistry.setDoctorName(" Bendu Appa Rao");
		doctorRegistry.setDoctorQualification("RMP");
		doctorRegistry.setYearsOfExperience(3);
		return doctorRegistry;

	}

	@Override
	public ServiceDTO<DoctorRegistry> saveDoctorRegistry(DoctorRegistry doctorRegistry) {
		ServiceDTO<DoctorRegistry> dto = new ServiceDTO<DoctorRegistry>();
		dto.setDataObject(doctorRegistry);
		try {
			validator.validateDoctorRegistry(dto);
			if (dto.getSeverity() < 5) {
				doctorRegistry = dao.save(doctorRegistry);
				dto.setDataObject(doctorRegistry);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public ServiceDTO<DoctorRegistry> retrieveDoctorRegistry(DoctorRegistry doctorRegistry) {
		ServiceDTO<DoctorRegistry> dto = new ServiceDTO<DoctorRegistry>();
		dto.setDataObject(doctorRegistry);

		try {
			if (doctorRegistry != null && doctorRegistry.getId() > 0) {
				DoctorRegistry dbDoctorRegistry = dao.retrieve(doctorRegistry);
				if (doctorRegistry != null) {
					dto.setDataObject(dbDoctorRegistry);
				} else {
					dto.addApplicationMessage("DR004", "No result found for given details", 5, "Id", doctorRegistry.getId());
				}
			} else {
				dto.addApplicationMessage("DR005", "Please enter doctor Id", 5, "Id", doctorRegistry.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public ServiceDTO<DoctorRegistry> deleteDoctorRegistry(DoctorRegistry doctorRegistry) {

		ServiceDTO<DoctorRegistry> dto = new ServiceDTO<DoctorRegistry>();
		dto.setDataObject(doctorRegistry);

		try {
			if (doctorRegistry != null && doctorRegistry.getId() > 0) {
				DoctorRegistry dbDoctorRegistry = dao.delete(doctorRegistry);
				if (dbDoctorRegistry != null) {
					dto.setDataObject(dbDoctorRegistry);
				} else {
					dto.addApplicationMessage("DR004", "No result found ", 5, "Id", doctorRegistry.getId());
				}
			} else {
				dto.addApplicationMessage("DR005", "Please enter hospital Id", 5, "Id", doctorRegistry.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public ServiceDTO<DoctorRegistry> saveDoctorRegistryByDoctorCode(DoctorRegistry doctorRegistry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceDTO<DoctorRegistry> retrieveDoctorRegistryByDoctorCode(DoctorRegistry doctorRegistry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceDTO<DoctorRegistry> deleteDoctorRegistryByDoctorCode(DoctorRegistry doctorRegistry) {
		ServiceDTO<DoctorRegistry> dto = new ServiceDTO<DoctorRegistry>();
		dto.setDataObject(doctorRegistry);

		try {
			if (doctorRegistry != null && doctorRegistry.getDoctorCode()!=null) {
				DoctorRegistry dbDoctorRegistry = dao.delete(doctorRegistry);
				if (dbDoctorRegistry != null) {
					dto.setDataObject(dbDoctorRegistry);
				} else {
					dto.addApplicationMessage("DR006", "No result found ", 5, "DoctorCode", doctorRegistry.getDoctorCode());
				}
			} else {
				dto.addApplicationMessage("DR007", "Please enter doctor code", 5, "DoctorCode", doctorRegistry.getDoctorCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}
