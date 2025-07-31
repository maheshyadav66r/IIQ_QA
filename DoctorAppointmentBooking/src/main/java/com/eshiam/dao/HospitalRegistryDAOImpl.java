
package com.eshiam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.eshiam.domains.DoctorRegistry;
import com.eshiam.domains.HospitalRegistry;
import com.eshiam.domains.HospitalToDoctor;
import com.eshiam.interfaces.HospitalRegistryDAO;

public class HospitalRegistryDAOImpl implements HospitalRegistryDAO {

	@Override
	public HospitalRegistry save(HospitalRegistry hospitalRegistry) {

		if (hospitalRegistry.getId() == 0) {
			try {
				insertHospitalRegistry(hospitalRegistry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			updateHospitalRegistry(hospitalRegistry);
		}
		return hospitalRegistry;
	}

	private void updateHospitalRegistry(HospitalRegistry hospitalRegistry) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			updateHospitalRegistryData(stmt, hospitalRegistry);
			saveHospitalToDoctorData(stmt, hospitalRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
	}

	private void saveHospitalToDoctorData(Statement stmt, HospitalRegistry hospitalRegistry) throws SQLException {
		for (HospitalToDoctor hospitalToDoctor : hospitalRegistry.getHospitalTodoctorList()) {
			if (hospitalToDoctor.getId() == 0) {
				insertHospitalToDoctorData(hospitalToDoctor, hospitalRegistry, stmt);
			} else {
				updateHospitalToDoctorData(hospitalToDoctor, hospitalRegistry, stmt);
			}
		}

	}

	private void updateHospitalToDoctorData(HospitalToDoctor hospitalToDoctor, HospitalRegistry hospitalRegistry, Statement stmt) throws SQLException {
		String updateHospitalToDoctorSQL = "update doctor_appointment.hospital_to_doctor set hospital_id=?,doctor_id=?,appointment_fee=?,start_hours=?,start_minutes=?,end_hours=?,end_minutes=? where id=?";
		updateHospitalToDoctorSQL = updateHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getHospitalId());
		updateHospitalToDoctorSQL = updateHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getDoctorId());
		updateHospitalToDoctorSQL = updateHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getAppointmentFee());
		updateHospitalToDoctorSQL = updateHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getStartHours());
		updateHospitalToDoctorSQL = updateHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getStartMinutes());
		updateHospitalToDoctorSQL = updateHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getId());
		updateHospitalToDoctorSQL = updateHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getEndHours());
		updateHospitalToDoctorSQL = updateHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getEndMinutes());

		System.out.println(updateHospitalToDoctorSQL);
		int count = stmt.executeUpdate(updateHospitalToDoctorSQL);
		System.out.println(count);

	}

	private void insertHospitalToDoctorData(HospitalToDoctor hospitalToDoctor, HospitalRegistry hospitalRegistry, Statement stmt) throws SQLException {
		hospitalToDoctor.setId(getMaxId(stmt, "select max(id) from doctor_appointment.hospital_to_doctor"));
		String insertHospitalToDoctorSQL = "INSERT INTO doctor_appointment.hospital_to_doctor (id,hospital_id,doctor_id,appointment_fee,start_hours,start_minutes,end_hours,end_minutes) Values(?,?,?,?,?,?,?,?)";
		insertHospitalToDoctorSQL = insertHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getId());
		insertHospitalToDoctorSQL = insertHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalRegistry.getId());
		// insertHospitalToDoctorSQL=insertHospitalToDoctorSQL.replaceFirst("\\?",
		// ""+hospitalToDoctor.getDoctorId());
		insertHospitalToDoctorSQL = insertHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getDoctorRegistry().getId());
		insertHospitalToDoctorSQL = insertHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getAppointmentFee());
		insertHospitalToDoctorSQL = insertHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getStartHours());
		insertHospitalToDoctorSQL = insertHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getStartMinutes());
		insertHospitalToDoctorSQL = insertHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getEndHours());
		insertHospitalToDoctorSQL = insertHospitalToDoctorSQL.replaceFirst("\\?", "" + hospitalToDoctor.getEndMinutes());

		System.out.println(insertHospitalToDoctorSQL);
		int count = stmt.executeUpdate(insertHospitalToDoctorSQL);
		System.out.println(count);
	}

	private void updateHospitalRegistryData(Statement stmt, HospitalRegistry hospitalRegistry) throws SQLException {

		String updateHospitalRegistryQuery = "update doctor_appointment.hospital_registry set hospital_code = ?,hospital_name = ?,hospital_start_date = ?,hospital_end_date = ? where id=?";
		updateHospitalRegistryQuery = updateHospitalRegistryQuery.replaceFirst("\\?", getQoutes(hospitalRegistry.getHospitalCode()));
		updateHospitalRegistryQuery = updateHospitalRegistryQuery.replaceFirst("\\?", getQoutes(hospitalRegistry.getHospitalName()));
		updateHospitalRegistryQuery = updateHospitalRegistryQuery.replaceFirst("\\?", getQoutes(getString(hospitalRegistry.getHospitalStartDate())));
		updateHospitalRegistryQuery = updateHospitalRegistryQuery.replaceFirst("\\?", getQoutes(getString(hospitalRegistry.getHospitalEndDate())));
		updateHospitalRegistryQuery = updateHospitalRegistryQuery.replaceFirst("\\?", "" + hospitalRegistry.getId());
		System.out.println(updateHospitalRegistryQuery);
		int count = stmt.executeUpdate(updateHospitalRegistryQuery);
		System.out.println(count);
	}

	private String getString(DateTime input) {
		String format = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String strDate = dateFormat.format(new Date(input.getMillis()));
		return strDate;
	}

	private void insertHospitalRegistry(HospitalRegistry hospitalRegistry) throws SQLException {
		Connection connection = null;
		Statement stmt = null;
		String getMaxId = "select max(id) from hospital_registry";
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			hospitalRegistry.setId(getMaxId(stmt, getMaxId));
			insertHospitalRegistryData(stmt, hospitalRegistry);
			saveHospitalToDoctorData(stmt, hospitalRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
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

	private void insertHospitalRegistryData(Statement stmt, HospitalRegistry hospitalRegistry) throws SQLException {
		String insertHospitalRegistryQuery = "insert into doctor_appointment.hospital_Registry(id,hospital_code,hospital_name,hospital_start_date,hospital_end_date) values (?,?,?,?,?)";

		insertHospitalRegistryQuery = insertHospitalRegistryQuery.replaceFirst("\\?", "" + hospitalRegistry.getId());
		insertHospitalRegistryQuery = insertHospitalRegistryQuery.replaceFirst("\\?", getQoutes(hospitalRegistry.getHospitalCode()));
		insertHospitalRegistryQuery = insertHospitalRegistryQuery.replaceFirst("\\?", getQoutes(hospitalRegistry.getHospitalName()));
		insertHospitalRegistryQuery = insertHospitalRegistryQuery.replaceFirst("\\?", getQoutes(getString(hospitalRegistry.getHospitalStartDate())));
		insertHospitalRegistryQuery = insertHospitalRegistryQuery.replaceFirst("\\?", getQoutes(getString(hospitalRegistry.getHospitalEndDate())));
		System.out.println(insertHospitalRegistryQuery);
		int count = stmt.executeUpdate(insertHospitalRegistryQuery);
		System.out.println(count);
	}

	private String getQoutes(String input) {
		return "'" + input + "'";
	}

	private int getMaxId(Statement stmt, String getMaxId) throws SQLException {
		ResultSet rs = stmt.executeQuery(getMaxId);
		int maxId = 0;
		if (rs.next()) {
			maxId = rs.getInt(1);
		}
		return ++maxId;
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
	public HospitalRegistry retrieve(HospitalRegistry hospitalRegistry) {
		Connection connection = null;
		java.sql.Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			retrieveHospitalRegistryData(stmt, hospitalRegistry);
			retrieveHospitalToDoctorData(stmt, hospitalRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}

		return hospitalRegistry;
	}

	private void retrieveHospitalToDoctorData(Statement stmt, HospitalRegistry hospitalRegistry) throws SQLException {
		String hospitalToDoctorDataSQL = "select * from doctor_appointment.hospital_to_doctor  where id=?";
		hospitalToDoctorDataSQL = hospitalToDoctorDataSQL.replaceFirst("\\?", "" + hospitalRegistry.getId());
		ResultSet rs = stmt.executeQuery(hospitalToDoctorDataSQL);
		List<HospitalToDoctor> hospitalToDoctorList = new ArrayList<>();
		while (rs.next()) {
			HospitalToDoctor htd = new HospitalToDoctor();
			htd.setId(rs.getInt("id"));
			htd.setHospitalId(rs.getInt("hospital_id"));
			htd.setDoctorId(rs.getInt("doctor_id"));
			htd.setAppointmentFee(rs.getInt("appointment_fee"));
			htd.setStartHours(rs.getInt("start_hours"));
			htd.setStartHours(rs.getInt("start_minutes"));
			htd.setStartHours(rs.getInt("end_hours"));
			htd.setStartHours(rs.getInt("end_minutes"));

			hospitalToDoctorList.add(htd);
			System.out.println(hospitalToDoctorList);
		}
		hospitalRegistry.setHospitalTodoctorList(hospitalToDoctorList);
		close(rs);
	}

	private void retrieveHospitalRegistryData(Statement stmt, HospitalRegistry hospitalRegistry) throws SQLException {
		String hospitalRegistryDataSQL = "select * from doctor_appointment.hospital_registry where id=?";
		hospitalRegistryDataSQL = hospitalRegistryDataSQL.replaceFirst("\\?", "" + hospitalRegistry.getId());

		ResultSet rs = stmt.executeQuery(hospitalRegistryDataSQL);
		if (rs.next()) {
			hospitalRegistry.setId(rs.getInt("id"));
			hospitalRegistry.setHospitalCode(rs.getString("hospital_code"));
			hospitalRegistry.setHospitalName(rs.getString("hospital_name"));
			hospitalRegistry.setHospitalStartDate(getDateTime(rs.getTimestamp("hospital_start_date")));
			hospitalRegistry.setHospitalEndDate(getDateTime(rs.getTimestamp("hospital_end_date")));
		}
		System.out.println(hospitalRegistry);

		close(rs);
	}

	private DateTime getDateTime(Timestamp timestamp) {
		DateTime dateTime = null;
		if (timestamp != null) {
			dateTime = new DateTime(new Date(timestamp.getTime()));
		}
		return dateTime;
	}

	@Override
	public HospitalRegistry delete(HospitalRegistry hospitalRegistry) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			deleteHospitalRegistryData(stmt, hospitalRegistry);
			deleteHospitalTodoctorData(stmt, hospitalRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
		return hospitalRegistry;
	}

	private void deleteHospitalTodoctorData(Statement stmt, HospitalRegistry hospitalRegistry) throws SQLException {
		String deleteHospitalTodoctorSQL = "delete from doctor_appointment.hospital_to_doctor where id=?";
		deleteHospitalTodoctorSQL = deleteHospitalTodoctorSQL.replaceFirst("\\?", "" + hospitalRegistry.getId());
		System.out.println(deleteHospitalTodoctorSQL);
		int count = stmt.executeUpdate(deleteHospitalTodoctorSQL);
	}

	private void deleteHospitalRegistryData(Statement stmt, HospitalRegistry hospitalRegistry) throws SQLException {
		String deleteHospitalRegistrySQL = "delete from doctor_appointment.hospital_registry where id=?";
		deleteHospitalRegistrySQL = deleteHospitalRegistrySQL.replaceFirst("\\?", "" + hospitalRegistry.getId());
		System.out.println(deleteHospitalRegistrySQL);
		int count = stmt.executeUpdate(deleteHospitalRegistrySQL);
		System.out.println(count);
	}

//	public static void main(String[] args) {
//		// HospitalRegistry hospitalRegistries = getHospitalRegistryObject();
//		HospitalRegistry hospitalRegistry = new HospitalRegistry();
//		//hospitalRegistry.setId(1);
//		HospitalRegistryDAOImpl hospitalRegistryDAOImpl = new HospitalRegistryDAOImpl();
//		HospitalRegistry dbhospitalRegistry = hospitalRegistryDAOImpl.save(getHospitalRegistryObject());
//		// System.out.println(dbhospitalRegistry);
//		System.out.println("Created Successfully...");
//
//	}

//	private static HospitalRegistry getHospitalRegistryObject() {
//		HospitalRegistry hospitalRegistry = new HospitalRegistry();
//		//hospitalRegistry.setId(1);
//		hospitalRegistry.setHospitalCode("nmg");
//		hospitalRegistry.setHospitalName("May");
//		hospitalRegistry.setHospitalStartDate(new DateTime());
//		hospitalRegistry.setHospitalEndDate(new DateTime());
//		HospitalToDoctor hospitalToDoctor1 = new HospitalToDoctor();
//		HospitalToDoctor hospitalToDoctor2 = new HospitalToDoctor();
//		HospitalToDoctor hospitalToDoctor3 = new HospitalToDoctor();
//
//		DoctorRegistry doctorRegistry1 = new DoctorRegistry(1,"ArjunReddy", "MBBS&M.S", 8);
//		DoctorRegistry doctorRegistry2 = new DoctorRegistry(2,"preethi", "MBBS", 5);
//		DoctorRegistry doctorRegistry3 = new DoctorRegistry(3,"Kamal", "MBBS&FRCS", 6);
//
//		hospitalToDoctor1.setDoctorRegistry(doctorRegistry1);
//		hospitalToDoctor2.setDoctorRegistry(doctorRegistry2);
//		hospitalToDoctor3.setDoctorRegistry(doctorRegistry3);
//
//		hospitalRegistry.getHospitalTodoctorList().add(hospitalToDoctor1);
//		hospitalRegistry.getHospitalTodoctorList().add(hospitalToDoctor2);
//		hospitalRegistry.getHospitalTodoctorList().add(hospitalToDoctor3);
//
//		return hospitalRegistry;
//
//	}
}