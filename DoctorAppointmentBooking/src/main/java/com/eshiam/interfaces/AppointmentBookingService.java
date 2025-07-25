package com.eshiam.interfaces;

import com.eshiam.domains.AppointmentBooking;
import com.eshiam.serviceDTOs.ServiceDTO;

public interface AppointmentBookingService {
public ServiceDTO<AppointmentBooking> saveAppointmentBooking(AppointmentBooking appointmentBooking);
public ServiceDTO<AppointmentBooking> retrieveAppointmentBooking(AppointmentBooking appointmentBooking);
public ServiceDTO<AppointmentBooking> deleteAppointmentBooking(AppointmentBooking appointmentBooking);

}
