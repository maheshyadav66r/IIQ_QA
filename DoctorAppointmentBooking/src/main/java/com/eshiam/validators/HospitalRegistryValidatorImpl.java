package com.eshiam.validators;

import java.util.Objects;

import org.joda.time.DateTime;

import com.eshiam.domains.HospitalRegistry;
import com.eshiam.domains.HospitalToDoctor;
import com.eshiam.interfaces.HospitalRegistryValidator;
import com.eshiam.serviceDTOs.ServiceDTO;

public class HospitalRegistryValidatorImpl implements HospitalRegistryValidator {

	public static void main(String[] args) {
		System.out.println("validator");

		 HospitalRegistryValidatorImpl hospitalRegistryValidatorImpl=new HospitalRegistryValidatorImpl();
		 
		 // hospitalRegistryValidatorImpl.validateAppointmentFee(hospitalTodoctorList, dto, 1);
		// HospitalRegistryValidatorImpl();

	}

	@Override
	public void validateHospitalRegistry(ServiceDTO<HospitalRegistry> dto) {
		HospitalRegistry hospitalRegistry = dto.getDataObject();

		if (hospitalRegistry != null) {
			validateHospitalCode(dto);
			validateHospitalName(dto); 
			validatehospitalStartDate(dto);
			validatehospitalEndDate(dto);
			validateHospitalTodoctorList(dto);

		}

	}

	private void validateHospitalTodoctorList(ServiceDTO<HospitalRegistry> dto) {
		HospitalRegistry hospitalRegistry = dto.getDataObject();
		if (hospitalRegistry.getHospitalTodoctorList() == null || hospitalRegistry.getHospitalTodoctorList().size() == 0) {
			dto.addApplicationMessage("HR005", "please enter atleast one act", 5, null, hospitalRegistry);
		} else {
			int index = 0;
			for (HospitalToDoctor hospitalToDoctor : hospitalRegistry.getHospitalTodoctorList()) {
				if (hospitalToDoctor != null) {
					validateAppointmentFee(hospitalToDoctor, dto, index);
					validateStartHours(hospitalToDoctor, dto, index);
					validateStartMinutes(hospitalToDoctor, dto, index);
					validateEndHours(hospitalToDoctor, dto, index);
					validateEndMinutes(hospitalToDoctor, dto, index);
					validateDoctorId(hospitalToDoctor, dto, index);
				}
				index++;
			}
		}
	}

	private void validateDoctorId(HospitalToDoctor hospitalToDoctor, ServiceDTO<HospitalRegistry> dto, int index) {
		if (hospitalToDoctor.getDoctorRegistry().getId() <= 0) {
			dto.addApplicationMessage("HR011", "please enter valid doctor id", 5, "hospitalToDoctor_" + index + "_doctorId", hospitalToDoctor.getDoctorRegistry().getId());
		}
	}		
	
	private void validateEndMinutes(HospitalToDoctor hospitalTodoctorLists, ServiceDTO<HospitalRegistry> dto, int index) {
		if (Objects.isNull(hospitalTodoctorLists.getEndMinutes())) {
			dto.addApplicationMessage("HR010", "please enter valid end minutes", 5, "hospitalToDoctor_"+index+"endMinutes", hospitalTodoctorLists.getStartMinutes());
		}
	}

	private void validateEndHours(HospitalToDoctor hospitalTodoctorLists, ServiceDTO<HospitalRegistry> dto, int index) {
		if (Objects.isNull(hospitalTodoctorLists.getEndHours())) {
			dto.addApplicationMessage("HR009", "please enter valid end hours", 5, "hospitalToDoctor_"+index+"_endHours", hospitalTodoctorLists.getEndHours());
		}
	}

	private void validateStartMinutes(HospitalToDoctor hospitalTodoctorLists, ServiceDTO<HospitalRegistry> dto, int index) {
		// HospitalToDoctor hospitalToDoctor = dto.getDataObject();
		if (Objects.isNull(hospitalTodoctorLists.getStartMinutes())) {
			dto.addApplicationMessage("HR008", "please enter valid start minutes", 5, "hospitalToDoctor_"+index+"_StartMinutes", hospitalTodoctorLists.getStartMinutes());
		}
	}

	private void validateStartHours(HospitalToDoctor hospitalTodoctorLists, ServiceDTO<HospitalRegistry> dto, int index) {
		// HospitalToDoctor hospitalToDoctor = dto.getDataObject();
		if (Objects.isNull(hospitalTodoctorLists.getStartHours())) {
			dto.addApplicationMessage("HR007", "please enter valid start hours", 5, "hospitalToDoctor_"+index+"_startHours", hospitalTodoctorLists.getStartHours());
		}
	}

	private void validateAppointmentFee(HospitalToDoctor hospitalTodoctorLists, ServiceDTO<HospitalRegistry> dto, int index) {
		if (hospitalTodoctorLists.getAppointmentFee() <= 0) {
			dto.addApplicationMessage("HR006", "please enter appointment fee", 5, "hospitalTodoctor_" + index + "_appointmentfee", hospitalTodoctorLists.getAppointmentFee());
		}
	}

	private void validatehospitalEndDate(ServiceDTO<HospitalRegistry> dto) {
		HospitalRegistry hospitalRegistry = dto.getDataObject();
		DateTime value = hospitalRegistry.getHospitalEndDate();
		if (value == null || value.compareTo(new DateTime()) >= 0) {
			dto.addApplicationMessage("HR004", "Please enter hospital end date", 5, "HospitalEndDate", hospitalRegistry.getHospitalEndDate());
		}
	}

	private void validatehospitalStartDate(ServiceDTO<HospitalRegistry> dto) {
		HospitalRegistry hospitalRegistry = dto.getDataObject();
		DateTime value = hospitalRegistry.getHospitalStartDate();
		if (value == null || value.compareTo(new DateTime()) >= 0) {
			dto.addApplicationMessage("HR003", "Please enter hospital start date", 5, "HospitalStartDate", hospitalRegistry.getHospitalStartDate());
		}
	}

	private void validateHospitalName(ServiceDTO<HospitalRegistry> dto) {
		HospitalRegistry hospitalRegistry = dto.getDataObject();
		if (isNull(hospitalRegistry.getHospitalName())) {
			dto.addApplicationMessage("HR002", "please enter hospital name", 5, "HospitalName", hospitalRegistry.getHospitalName());
		}
	}

	private void validateHospitalCode(ServiceDTO<HospitalRegistry> dto) {
		HospitalRegistry hospitalRegistry = dto.getDataObject();
		if (isNull(hospitalRegistry.getHospitalCode())) {
			dto.addApplicationMessage("HR0001", "Please enter  hospital code", 5, "HospitalCode", hospitalRegistry.getHospitalCode());
		}

	}

	private boolean isNull(String value) {
		return value == null || value.trim().length() == 0;
	}

}
