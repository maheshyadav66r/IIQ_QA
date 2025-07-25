package com.eshiam.servicelayers;

import com.eshiam.dao.DoctorsTimeOffDAOImpl;
import com.eshiam.domains.DoctorsTimeOff;
import com.eshiam.interfaces.DoctorsTimeOffDAO;
import com.eshiam.interfaces.DoctorsTimeOffService;
import com.eshiam.interfaces.DoctorsTimeOffValidator;
import com.eshiam.serviceDTOs.ServiceDTO;
import com.eshiam.validators.DoctorsTimeOffValidatorImpl;

public class DoctorsTimeOffServiceImpl implements DoctorsTimeOffService {
	private DoctorsTimeOffValidator validator = new DoctorsTimeOffValidatorImpl();
	private DoctorsTimeOffDAO dao = new DoctorsTimeOffDAOImpl();

	@Override
	public ServiceDTO<DoctorsTimeOff> saveDoctorsTimeOff(DoctorsTimeOff doctorsTimeOff) {
		ServiceDTO<DoctorsTimeOff> dto = new ServiceDTO<DoctorsTimeOff>();
		dto.setDataObject(doctorsTimeOff);
		try {
			validator.validateDoctorsTimeOff(dto);
			if (dto.getSeverity() < 5) {
				doctorsTimeOff = dao.save(doctorsTimeOff);
				dto.setDataObject(doctorsTimeOff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public ServiceDTO<DoctorsTimeOff> retrieveDoctorsTimeOff(DoctorsTimeOff doctorsTimeOff) {
		ServiceDTO<DoctorsTimeOff> dto = new ServiceDTO<DoctorsTimeOff>();
		dto.setDataObject(doctorsTimeOff);
		try {
			if (doctorsTimeOff != null && doctorsTimeOff.getId() > 0) {
				DoctorsTimeOff dbDoctorsTimeOff = dao.retrieve(doctorsTimeOff);
				if (doctorsTimeOff != null) {
					dto.setDataObject(dbDoctorsTimeOff);
				} else {
					dto.addApplicationMessage("DT004", "No result found for given details", 5, "Id", doctorsTimeOff.getId());
				}
			} else {
				dto.addApplicationMessage("DT005", "Please enter  Id", 5, "Id", doctorsTimeOff.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public ServiceDTO<DoctorsTimeOff> deleteDoctorsTimeOff(DoctorsTimeOff doctorsTimeOff) {
		ServiceDTO<DoctorsTimeOff> dto = new ServiceDTO<DoctorsTimeOff>();
		dto.setDataObject(doctorsTimeOff);

		try {
			if (doctorsTimeOff != null && doctorsTimeOff.getId() > 0) {
				DoctorsTimeOff dbDoctorsTimeOff = dao.delete(doctorsTimeOff);
				if (dbDoctorsTimeOff != null) {
					dto.setDataObject(dbDoctorsTimeOff);
				} else {
					dto.addApplicationMessage("AB004", "No result found ", 5, "Id", doctorsTimeOff.getId());
				}
			} else {
				dto.addApplicationMessage("AB005", "Please enter hospital Id", 5, "Id", doctorsTimeOff.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public static void main(String[] args) {

	}

}
