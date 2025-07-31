package com.eshiam.validators;

import com.eshiam.domains.AppointmentBooking;
import com.eshiam.interfaces.AppointmentBookingValidator;
import com.eshiam.serviceDTOs.ServiceDTO;

public class AppointmentBookingValidatorImpl implements AppointmentBookingValidator {

	@Override
	public void validateAppointmentBooking(ServiceDTO<AppointmentBooking> dto) {
		AppointmentBooking appointmentBooking = new AppointmentBooking();
		if (appointmentBooking != null) {
			validatePatientName(dto);
			validateBookingStartTime(dto);
			validateBookingEndTime(dto);
			validateAppointmentFee(dto);
			validateAppointmentStatus(dto);
			validateRescheduleStartTime(dto);
			validateRescheduleEndTime(dto);
		}

	}

	private void validateRescheduleEndTime(ServiceDTO<AppointmentBooking> dto) {
		AppointmentBooking appointmentBooking = dto.getDataObject();
		if (isNull(appointmentBooking.getRescheduleEndTime())) {
			dto.addApplicationMessage("AB007", "please enter reschedule end time", 5, "RescheduleStartTime", appointmentBooking.getRescheduleEndTime());
		}
	}

	private void validateRescheduleStartTime(ServiceDTO<AppointmentBooking> dto) {
		AppointmentBooking appointmentBooking = dto.getDataObject();
		if (isNull(appointmentBooking.getRescheduleStartTime())) {
			dto.addApplicationMessage("AB006", "please enter reschedule start time", 5, "RescheduleStartTime", appointmentBooking.getRescheduleStartTime());
		}
	}

	private void validateAppointmentStatus(ServiceDTO<AppointmentBooking> dto) {
		AppointmentBooking appointmentBooking = dto.getDataObject();
		if (isNull(appointmentBooking.getAppointmentStatus())) {
			dto.addApplicationMessage("AB005", "please enter appointment status", 5, "AppointmentStatus", appointmentBooking.getAppointmentStatus());
		}
	}

	private void validateAppointmentFee(ServiceDTO<AppointmentBooking> dto) {
		AppointmentBooking appointmentBooking = dto.getDataObject();
		if (appointmentBooking.getAppointmentFee() > 0) {
			dto.addApplicationMessage("AB004", "please enter appointment fee", 5, "AppointmentFee", appointmentBooking.getAppointmentFee());
		}
	}

	private void validateBookingEndTime(ServiceDTO<AppointmentBooking> dto) {
		AppointmentBooking appointmentBooking = dto.getDataObject();
		if (isNull(appointmentBooking.getBookingEndTime())) {
			dto.addApplicationMessage("AB003", "please enter booking end time", 5, "BookingEndTime", appointmentBooking.getBookingEndTime());
		}
	}

	private void validateBookingStartTime(ServiceDTO<AppointmentBooking> dto) {
		AppointmentBooking appointmentBooking = dto.getDataObject();
		if (isNull(appointmentBooking.getBookingStartTime())) {
			dto.addApplicationMessage("AB002", "Please enter booking start time", 5, "BookingStartTime", appointmentBooking.getBookingStartTime());
		}

	}

	private void validatePatientName(ServiceDTO<AppointmentBooking> dto) {
		AppointmentBooking appointmentBooking = dto.getDataObject();
		if (isNull(appointmentBooking.getPatientName())) {
			dto.addApplicationMessage("AB001", "Please enter patient name", 5, "PatientName", appointmentBooking.getPatientName());
		}
	}

	private boolean isNull(String value) {
		// TODO Auto-generated method stub
		return value == null || value.trim().length() == 0;
	}

}

/*
 * private String patientName; private String bookingStartTime; private String
 * bookingEndTime; private int appointmentFee; private String appointmentStatus;
 * // cancel or reschedule or booked private String rescheduleStartTime; private
 * String rescheduleEndTime;
 */