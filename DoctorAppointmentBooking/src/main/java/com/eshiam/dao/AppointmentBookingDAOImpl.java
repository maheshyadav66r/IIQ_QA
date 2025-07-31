package com.eshiam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.eshiam.domains.AppointmentBooking;
import com.eshiam.interfaces.AppointmentBookingDAO;

public class AppointmentBookingDAOImpl implements AppointmentBookingDAO {

	@Override
	public AppointmentBooking save(AppointmentBooking appointmentBooking) throws SQLException {
		if (appointmentBooking.getId() == 0) {
			insertAppointmentBooking(appointmentBooking);
		} else {
			updateAppointmentBooking(appointmentBooking);
		}
		return appointmentBooking;
	}

	private void updateAppointmentBooking(AppointmentBooking appointmentBooking) throws SQLException {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			updateAppointmentBookingData(stmt, appointmentBooking);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
	}

	private void updateAppointmentBookingData(Statement stmt, AppointmentBooking appointmentBooking) throws SQLException {

		String updateAppointmentBookingQuery = "update doctor_appointment.appointment_booking set patient_name=?,booking_start_time=?,booking_end_time=?,appointment_fee=?,appointment_status=?,reschedule_start_time=?,reschedule_end_time=? where id=?";
		updateAppointmentBookingQuery = updateAppointmentBookingQuery.replace("\\?", getQoutes(appointmentBooking.getPatientName()));
		updateAppointmentBookingQuery = updateAppointmentBookingQuery.replace("\\?", getQoutes(appointmentBooking.getBookingStartTime()));
		updateAppointmentBookingQuery = updateAppointmentBookingQuery.replace("\\?", getQoutes(appointmentBooking.getBookingEndTime()));
		updateAppointmentBookingQuery = updateAppointmentBookingQuery.replace("\\?", "" + appointmentBooking.getAppointmentFee());
		updateAppointmentBookingQuery = updateAppointmentBookingQuery.replace("\\?", getQoutes(appointmentBooking.getAppointmentStatus()));
		updateAppointmentBookingQuery = updateAppointmentBookingQuery.replace("\\?", getQoutes(appointmentBooking.getRescheduleStartTime()));
		updateAppointmentBookingQuery = updateAppointmentBookingQuery.replace("\\?", getQoutes(appointmentBooking.getRescheduleEndTime()));
		updateAppointmentBookingQuery = updateAppointmentBookingQuery.replace("\\?", "" + appointmentBooking.getId());

		System.out.println(updateAppointmentBookingQuery);
		int count = stmt.executeUpdate(updateAppointmentBookingQuery);
		System.out.println(count);
	}

	/*
	 * patient_name booking_start_time booking_end_time appointment_fee
	 * appointment_status reschedule_start_time reschedule_end_time
	 */
	private void insertAppointmentBooking(AppointmentBooking appointmentBooking) {
		Connection connection = null;
		Statement stmt = null;
		String getMaxId = "select max(id) from appointment_booking";

		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			appointmentBooking.setId(getMaxId(stmt, getMaxId));
			insertAppointmentBookingData(stmt, appointmentBooking);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection);
			close(stmt);
		}
	}

	private void insertAppointmentBookingData(Statement stmt, AppointmentBooking appointmentBooking) throws SQLException {
		String insertAppointmentBookingQuery = "insert into doctor_appointment.appointment_booking(id,patient_name,booking_start_time,booking_end_time,appointment_fee,appointment_status,reschedule_start_time,reschedule_end_time) values(?,?,?,?,?,?,?,?)";
		insertAppointmentBookingQuery = insertAppointmentBookingQuery.replaceFirst("\\?", "" + appointmentBooking.getId());
		insertAppointmentBookingQuery = insertAppointmentBookingQuery.replaceFirst("\\?", getQoutes(appointmentBooking.getPatientName()));
		insertAppointmentBookingQuery = insertAppointmentBookingQuery.replaceFirst("\\?", getQoutes(appointmentBooking.getBookingStartTime()));
		insertAppointmentBookingQuery = insertAppointmentBookingQuery.replaceFirst("\\?", getQoutes(appointmentBooking.getBookingEndTime()));
		insertAppointmentBookingQuery = insertAppointmentBookingQuery.replaceFirst("\\?", "" + appointmentBooking.getAppointmentFee());
		insertAppointmentBookingQuery = insertAppointmentBookingQuery.replaceFirst("\\?", getQoutes(appointmentBooking.getAppointmentStatus()));
		insertAppointmentBookingQuery = insertAppointmentBookingQuery.replaceFirst("\\?", getQoutes(appointmentBooking.getRescheduleStartTime()));
		insertAppointmentBookingQuery = insertAppointmentBookingQuery.replaceFirst("\\?", getQoutes(appointmentBooking.getRescheduleEndTime()));
		System.out.println(insertAppointmentBookingQuery);
		int count = stmt.executeUpdate(insertAppointmentBookingQuery);
		System.out.println(count);
	}

	private String getQoutes(String input) {
		return "'" + input + "'";
	}

	private int getMaxId(Statement stmt, String getMaxId) throws SQLException {
		ResultSet rs = ((java.sql.Statement) stmt).executeQuery(getMaxId);
		int maxId = 0;
		if (rs.next()) {
			maxId = rs.getInt(1);
		}
		
		return ++maxId;
	}

	private void close(AutoCloseable closable) {
		try {
			if (closable != null) {
				closable.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Connection getDBConnection() {
		Connection connection = null;
		try {
			Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor_appointment", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public AppointmentBooking retrieve(AppointmentBooking appointmentBooking) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			retrieveAppointmentBookingData(stmt, appointmentBooking);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void retrieveAppointmentBookingData(Statement stmt, AppointmentBooking appointmentBooking) throws SQLException {
		String retrieveAppointmentBookingQuery = "select * from doctor_appointment.appointment_booking where id=?";
		retrieveAppointmentBookingQuery = retrieveAppointmentBookingQuery.replaceFirst("\\?", "" + appointmentBooking.getId());
		ResultSet rs = stmt.executeQuery(retrieveAppointmentBookingQuery);
		if (rs.next()) {
			appointmentBooking.setId(rs.getInt("id"));
			appointmentBooking.setPatientName(rs.getString("patient_name"));
			appointmentBooking.setBookingStartTime(rs.getString("booking_start_time"));
			appointmentBooking.setBookingEndTime(rs.getString("booking_end_time"));
			appointmentBooking.setAppointmentFee(rs.getInt("appintment_fee"));
			appointmentBooking.setAppointmentStatus(rs.getString("appointment_status"));
			appointmentBooking.setRescheduleStartTime(rs.getString("reschedule_start_time"));
			appointmentBooking.setRescheduleEndTime(rs.getString("reschedule_end_time"));
		}
		System.out.println(appointmentBooking);
		close(rs);
	}

	@Override
	public AppointmentBooking delete(AppointmentBooking appointmentBooking) {

		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			deleteAppointmentBookingData(stmt, appointmentBooking);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appointmentBooking;
	}

	private void deleteAppointmentBookingData(Statement stmt, AppointmentBooking appointmentBooking) throws SQLException {
		String deleteAppointmentBookingQuery = "delete from doctor_appointment.appointment_booking where id=?";
		deleteAppointmentBookingQuery = deleteAppointmentBookingQuery.replaceFirst("\\?", "" + appointmentBooking.getId());
		System.out.println(deleteAppointmentBookingQuery);
		int count = stmt.executeUpdate(deleteAppointmentBookingQuery);
		System.out.println(count);
	}

	public static void main(String[] args) throws SQLException {
//		AppointmentBooking appointmentBooking = getAppointmentBookingObject();
//		AppointmentBookingDAOImpl appointmentBookingDAOImpl = new AppointmentBookingDAOImpl();
//		AppointmentBooking dbAppointmentBooking = appointmentBookingDAOImpl.save(getAppointmentBookingObject());
//		System.out.println("created..DAO");
		
		
		AppointmentBooking appointmentBooking = getAppointmentBookingObject();
		AppointmentBookingDAOImpl appointmentBookingDAOImpl=new AppointmentBookingDAOImpl();
		AppointmentBooking dbAppointmentBooking = appointmentBookingDAOImpl.save(getAppointmentBookingObject());
		System.out.println("Created Successfully...");

	}

	private static AppointmentBooking getAppointmentBookingObject() {
		AppointmentBooking appointmentBooking = new AppointmentBooking();
		appointmentBooking.setId(0);
		appointmentBooking.setPatientName("pavan");
		appointmentBooking.setBookingStartTime("10:30am");
		appointmentBooking.setBookingEndTime("11:40am");
		appointmentBooking.setAppointmentFee(2000);
		appointmentBooking.setAppointmentStatus("pending");
		appointmentBooking.setRescheduleStartTime("2:30pm");
		appointmentBooking.setRescheduleEndTime("4:30pm");

		return appointmentBooking;
	}
}
