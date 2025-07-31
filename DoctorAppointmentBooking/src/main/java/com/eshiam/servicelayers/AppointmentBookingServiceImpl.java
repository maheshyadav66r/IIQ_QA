package com.eshiam.servicelayers;

import java.sql.SQLException;

import com.eshiam.dao.AppointmentBookingDAOImpl;
import com.eshiam.domains.AppointmentBooking;
import com.eshiam.interfaces.AppointmentBookingDAO;
import com.eshiam.interfaces.AppointmentBookingService;
import com.eshiam.interfaces.AppointmentBookingValidator;
import com.eshiam.serviceDTOs.ServiceDTO;
import com.eshiam.validators.AppointmentBookingValidatorImpl;

public class AppointmentBookingServiceImpl implements AppointmentBookingService {

	private AppointmentBookingValidator validator = new AppointmentBookingValidatorImpl();
	private AppointmentBookingDAO dao = new AppointmentBookingDAOImpl();

	public static void main(String[] args) throws SQLException {
		AppointmentBookingServiceImpl appointmentBookingServiceImpl=new AppointmentBookingServiceImpl();
		//appointmentBookingServiceImpl.saveAppointmentBooking(getAppointmentBookingObject());
		appointmentBookingServiceImpl.retrieveAppointmentBooking(getAppointmentBookingObject());
		//appointmentBookingServiceImpl.deleteAppointmentBooking(getAppointmentBookingObject());

		
		AppointmentBookingDAOImpl  appointmentBookingDAOImpl=new AppointmentBookingDAOImpl();
		//appointmentBookingDAOImpl.save(getAppointmentBookingObject());
		appointmentBookingDAOImpl.retrieve(getAppointmentBookingObject());
		//appointmentBookingDAOImpl.delete(getAppointmentBookingObject());

		
	}
	private static AppointmentBooking getAppointmentBookingObject() {
		AppointmentBooking appointmentBooking=new AppointmentBooking();
		appointmentBooking.setId(1);
		
//		appointmentBooking.setPatientName("pavan");
//		appointmentBooking.setBookingStartTime("12:30");
//		appointmentBooking.setBookingEndTime("1:30");
//		appointmentBooking.setAppointmentFee(1200);
//		appointmentBooking.setAppointmentStatus("success");
//		appointmentBooking.setRescheduleStartTime("14:00");
//		appointmentBooking.setRescheduleEndTime("14:30");
//		
	
		return appointmentBooking;
	}
	
	
	/*private int id;
	private String patientName;
	private String bookingStartTime;
	private String bookingEndTime;
	private int appointmentFee;
	private String appointmentStatus; // cancel or reschedule or booked
	private String rescheduleStartTime;
	private String rescheduleEndTime;
	private String action;*/
	@Override
	public ServiceDTO<AppointmentBooking> saveAppointmentBooking(AppointmentBooking appointmentBooking) {
		ServiceDTO<AppointmentBooking> dto = new ServiceDTO<AppointmentBooking>();
		dto.setDataObject(appointmentBooking);
		try {
			validator.validateAppointmentBooking(dto);
			if (dto.getSeverity() < 5) {
				appointmentBooking = dao.save(appointmentBooking);
				dto.setDataObject(appointmentBooking);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public ServiceDTO<AppointmentBooking> retrieveAppointmentBooking(AppointmentBooking appointmentBooking) {
		ServiceDTO<AppointmentBooking> dto = new ServiceDTO<AppointmentBooking>();
		dto.setDataObject(appointmentBooking);

		try {
			if (appointmentBooking != null && appointmentBooking.getId() > 0) {
				AppointmentBooking dbAppointmentBooking = dao.retrieve(appointmentBooking);
				if (appointmentBooking != null) {
					dto.setDataObject(dbAppointmentBooking);
				} else {
					dto.addApplicationMessage("AB004", "No result found for given details", 5, "Id", appointmentBooking.getId());
				}
			} else {
				dto.addApplicationMessage("AB005", "Please enter  Id", 5, "Id", appointmentBooking.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public ServiceDTO<AppointmentBooking> deleteAppointmentBooking(AppointmentBooking appointmentBooking) {
		ServiceDTO<AppointmentBooking> dto = new ServiceDTO<AppointmentBooking>();
		dto.setDataObject(appointmentBooking);

		try {
			if (appointmentBooking != null && appointmentBooking.getId() > 0) {
				AppointmentBooking dbAppointmentBooking = dao.delete(appointmentBooking);
				if (dbAppointmentBooking != null) {
					dto.setDataObject(dbAppointmentBooking);
				} else {
					dto.addApplicationMessage("AB004", "No result found ", 5, "Id", appointmentBooking.getId());
				}
			} else {
				dto.addApplicationMessage("AB005", "Please enter hospital Id", 5, "Id", appointmentBooking.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}
