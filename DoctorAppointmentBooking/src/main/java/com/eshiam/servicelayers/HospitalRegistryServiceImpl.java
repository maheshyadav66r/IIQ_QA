package com.eshiam.servicelayers;


import org.joda.time.DateTime;

import com.eshiam.dao.DoctorRegistryDAOImpl;
import com.eshiam.dao.HospitalRegistryDAOImpl;
import com.eshiam.domains.HospitalRegistry;
import com.eshiam.domains.HospitalToDoctor;
import com.eshiam.interfaces.DoctorRegistryDAO;
import com.eshiam.interfaces.HospitalRegistryDAO;
import com.eshiam.interfaces.HospitalRegistryService;
import com.eshiam.interfaces.HospitalRegistryValidator;
import com.eshiam.serviceDTOs.ServiceDTO;
import com.eshiam.validators.HospitalRegistryValidatorImpl;

public class HospitalRegistryServiceImpl implements HospitalRegistryService {

	private HospitalRegistryValidator validator = new HospitalRegistryValidatorImpl();
	private HospitalRegistryDAO dao = new HospitalRegistryDAOImpl();
	private DoctorRegistryDAO ddao = new DoctorRegistryDAOImpl();

	public static void main(String[] args) {

		HospitalRegistryServiceImpl hospitalRegistryServiceImpl = new HospitalRegistryServiceImpl();

		// hospitalRegistryServiceImpl.saveHospitalRegistry(getHospitalRegistryObject());
//		 hospitalRegistryServiceImpl.retrieveHospitalRegistry(getHospitalRegistryObject());
 hospitalRegistryServiceImpl.deleteHospitalRegistry(getHospitalRegistryObject());

	HospitalRegistryDAOImpl hospitalRegistryDAOImpl = new HospitalRegistryDAOImpl();
		// hospitalRegistryDAOImpl.save(getHospitalRegistryObject());
//		 hospitalRegistryDAOImpl.retrieve(getHospitalRegistryObject());
	hospitalRegistryDAOImpl.delete(getHospitalRegistryObject());
	}

	private static HospitalRegistry getHospitalRegistryObject() {
		HospitalRegistry hospitalRegistry = new HospitalRegistry();
		hospitalRegistry.setId(3);
//		hospitalRegistry.setHospitalCode("Waseem");
//		hospitalRegistry.setHospitalName("yashodha");
//		hospitalRegistry.setHospitalStartDate(new DateTime());
//		hospitalRegistry.setHospitalEndDate(new DateTime());

//		HospitalToDoctor htd1 = new HospitalToDoctor(256, 9, 1500, 8, 30, 16, 45);
//		HospitalToDoctor htd2 = new HospitalToDoctor(14, 20, 2000, 9, 20, 16, 25);
//		HospitalToDoctor htd3 = new HospitalToDoctor(145, 2200, 2000, 9, 20, 16, 25);
//
//		hospitalRegistry.getHospitalTodoctorList().add(htd1);
//		hospitalRegistry.getHospitalTodoctorList().add(htd2);
//		hospitalRegistry.getHospitalTodoctorList().add(htd3);

		return hospitalRegistry;
	}

	@Override
	public ServiceDTO<HospitalRegistry> saveHospitalRegistry(HospitalRegistry hospitalRegistry) {
		ServiceDTO<HospitalRegistry> dto = new ServiceDTO<HospitalRegistry>();
		dto.setDataObject(hospitalRegistry);
		try {
			populateDoctorDetails(dto);
			validator.validateHospitalRegistry(dto);
			if (dto.getSeverity() < 5) {
				hospitalRegistry = dao.save(hospitalRegistry);
				dto.setDataObject(hospitalRegistry);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	private void populateDoctorDetails(ServiceDTO<HospitalRegistry> dto) {
		try {
			HospitalRegistry hospitalRegistry = dto.getDataObject();
			for (HospitalToDoctor doctor : hospitalRegistry.getHospitalTodoctorList()) {
				int id = ddao.retrieveDoctorIdByDoctorName(doctor.getDoctorRegistry().getDoctorName());
				doctor.setDoctorId(id);
				doctor.getDoctorRegistry().setId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ServiceDTO<HospitalRegistry> retrieveHospitalRegistry(HospitalRegistry hospitalRegistry) {

		ServiceDTO<HospitalRegistry> dto = new ServiceDTO<HospitalRegistry>();
		dto.setDataObject(hospitalRegistry);

		try {
			if (hospitalRegistry != null && hospitalRegistry.getId() > 0) {
				HospitalRegistry dbHospitalRegistry = dao.retrieve(hospitalRegistry);
				if (dbHospitalRegistry != null) {
					dto.setDataObject(dbHospitalRegistry);
				} else {
					dto.addApplicationMessage("HR012", "No result found for given details", 5, "Id", hospitalRegistry.getId());
				}
			} else {
				dto.addApplicationMessage("HR013", "Please enter hospital Id", 5, "Id", hospitalRegistry.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public ServiceDTO<HospitalRegistry> deleteHospitalRegistry(HospitalRegistry hospitalRegistry) {
		ServiceDTO<HospitalRegistry> dto = new ServiceDTO<HospitalRegistry>();
		dto.setDataObject(hospitalRegistry);

		try {
			if (hospitalRegistry != null && hospitalRegistry.getId() > 0) {
				HospitalRegistry dbHospitalRegistry = dao.delete(hospitalRegistry);
				if (dbHospitalRegistry != null) {
					dto.setDataObject(dbHospitalRegistry);
				} else {
					dto.addApplicationMessage("HR012", "No result found ", 5, "Id", hospitalRegistry.getId());
				}
			} else {
				dto.addApplicationMessage("HR013", "Please enter hospital Id", 5, "Id", hospitalRegistry.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}
