package com.eshiam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;

import com.eshiam.domains.DoctorsTimeOff;
import com.eshiam.interfaces.DoctorsTimeOffDAO;

public class DoctorsTimeOffDAOImpl implements DoctorsTimeOffDAO {

	@Override
	public DoctorsTimeOff save(DoctorsTimeOff doctorsTimeOff) {
		if (doctorsTimeOff.getId() == 0) {
			insertDoctorsTimeOff(doctorsTimeOff);
		} else {
			updateDoctorsTimeOff(doctorsTimeOff);
		}

		return doctorsTimeOff;
	}

	private void updateDoctorsTimeOff(DoctorsTimeOff doctorsTimeOff) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			updateDoctorsTimeOffData(stmt, doctorsTimeOff);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
	}

	private void updateDoctorsTimeOffData(Statement stmt, DoctorsTimeOff doctorsTimeOff) throws SQLException {
		String updateDoctorsTimeOffQuery = "update doctor_appointment.doctor_time_offdoctor_appointment.doctor_time_off set start_date=?,end_date=? where id=?";
		updateDoctorsTimeOffQuery = updateDoctorsTimeOffQuery.replaceFirst("\\?", "" + doctorsTimeOff.getId());
		System.out.println(updateDoctorsTimeOffQuery);
		int count = stmt.executeUpdate(updateDoctorsTimeOffQuery);
		System.out.println(updateDoctorsTimeOffQuery);
	}

	private void insertDoctorsTimeOff(DoctorsTimeOff doctorsTimeOff) {
		Connection connection = null;
		Statement stmt = null;
		String getMaxId = "select max(id) from doctor_time_off";
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			doctorsTimeOff.setId(getMaxId(stmt, getMaxId));
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
	public DoctorsTimeOff retrieve(DoctorsTimeOff doctorsTimeOff) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			retrieveDoctorsTimeOffData(stmt, doctorsTimeOff);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
		return doctorsTimeOff;
	}

	private void retrieveDoctorsTimeOffData(Statement stmt, DoctorsTimeOff doctorsTimeOff) throws SQLException {
		String retrieveDoctorsTimeOffQuery = "select from doctor_appointment.doctor_time_off where id=?";
		retrieveDoctorsTimeOffQuery = retrieveDoctorsTimeOffQuery.replaceFirst("\\?", "" + doctorsTimeOff.getId());

		ResultSet rs = stmt.executeQuery(retrieveDoctorsTimeOffQuery);
		if (rs.next()) {
			doctorsTimeOff.setId(rs.getInt("id"));
			doctorsTimeOff.setStartDate(getDateTime(rs.getTimestamp("start_date")));
			doctorsTimeOff.setEndDate(getDateTime(rs.getTimestamp("end_date")));
		}
		System.out.println(doctorsTimeOff);
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
	public DoctorsTimeOff delete(DoctorsTimeOff doctorsTimeOff) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			deleteDoctorsTimeOffData(stmt, doctorsTimeOff);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
		return doctorsTimeOff;
	}

	private void deleteDoctorsTimeOffData(Statement stmt, DoctorsTimeOff doctorsTimeOff) throws SQLException {
		String deleteDoctorsTimeOffQuery = "delete from doctor_appointment.doctor_time_off where id=?";
		deleteDoctorsTimeOffQuery = deleteDoctorsTimeOffQuery.replaceFirst("\\?", "" + doctorsTimeOff.getId());
		System.out.println(deleteDoctorsTimeOffQuery);
		int count = stmt.executeUpdate(deleteDoctorsTimeOffQuery);
		System.out.println(deleteDoctorsTimeOffQuery);
	}

	public static void main(String[] args) {

		DoctorsTimeOffDAOImpl doctorsTimeOffDAOImpl = new DoctorsTimeOffDAOImpl();
		DoctorsTimeOff dbDoctorsTimeOff = doctorsTimeOffDAOImpl.save(getDoctorsTimeOffObject());

	}

	private static DoctorsTimeOff getDoctorsTimeOffObject() {
		DoctorsTimeOff doctorsTimeOff = new DoctorsTimeOff();

		doctorsTimeOff.setId(0);
		doctorsTimeOff.setStartDate(new DateTime());
		doctorsTimeOff.setEndDate(new DateTime());

		System.out.println("doctor_appointment.doctor_time_off");
		return doctorsTimeOff;
	}
}
