package com.eshiam.interfaces;

import com.eshiam.domains.AppointmentBooking;
import com.eshiam.serviceDTOs.ServiceDTO;

public interface AppointmentBookingValidator {
public void validateAppointmentBooking(ServiceDTO<AppointmentBooking> dto);
}
