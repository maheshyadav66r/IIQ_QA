package com.eshiam.interfaces;

import java.sql.SQLException;

import com.eshiam.domains.AppointmentBooking;

public interface AppointmentBookingDAO {
public  AppointmentBooking save(AppointmentBooking appointmentBooking) throws SQLException;
public  AppointmentBooking retrieve(AppointmentBooking appointmentBooking);
public  AppointmentBooking delete(AppointmentBooking appointmentBooking);

}
