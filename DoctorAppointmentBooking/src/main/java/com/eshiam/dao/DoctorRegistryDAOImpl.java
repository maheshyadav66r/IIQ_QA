package com.eshiam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.eshiam.domains.DoctorRegistry;
import com.eshiam.interfaces.DoctorRegistryDAO;
import com.mysql.cj.protocol.Resultset;

public class DoctorRegistryDAOImpl implements DoctorRegistryDAO {

	@Override
	public DoctorRegistry save(DoctorRegistry doctorRegistry) {
		if (doctorRegistry.getId() == 0) {
			insertDoctorRegistry(doctorRegistry);
		} else {
			updateDoctorRegistry(doctorRegistry);
		}
		return doctorRegistry;
	}

	private void updateDoctorRegistry(DoctorRegistry doctorRegistry) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			updateDoctorRegistryData(stmt, doctorRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
	}

	private void updateDoctorRegistryData(Statement stmt, DoctorRegistry doctorRegistry) throws SQLException {
		String updateDoctorRegistryQuery = "update doctor_appointment.doctor_registry set doctor_code=?,doctor_name=?,doctor_qualification=?,years_of_experience=? where id=?";
		updateDoctorRegistryQuery = updateDoctorRegistryQuery.replaceFirst("\\?", getQoutes(doctorRegistry.getDoctorCode()));
		updateDoctorRegistryQuery = updateDoctorRegistryQuery.replaceFirst("\\?", getQoutes(doctorRegistry.getDoctorName()));
		updateDoctorRegistryQuery = updateDoctorRegistryQuery.replaceFirst("\\?", getQoutes(doctorRegistry.getDoctorQualification()));
		updateDoctorRegistryQuery = updateDoctorRegistryQuery.replaceFirst("\\?", "" + doctorRegistry.getYearsOfExperience());
		updateDoctorRegistryQuery = updateDoctorRegistryQuery.replaceFirst("\\?", "" + doctorRegistry.getId());
		System.out.println(updateDoctorRegistryQuery);
		int count = stmt.executeUpdate(updateDoctorRegistryQuery);
		System.out.println(updateDoctorRegistryQuery);
	}

	private void insertDoctorRegistry(DoctorRegistry doctorRegistry) {
		Connection connection = null;
		Statement stmt = null;
		String getMaxId = "select max(id) from doctor_registry";
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			doctorRegistry.setId(getMaxId(stmt, getMaxId));
			insertDoctorRegistryData(stmt, doctorRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
	}

	private void insertDoctorRegistryData(Statement stmt, DoctorRegistry doctorRegistry) throws SQLException {
		String insertDoctorRegistryQuery = "insert into doctor_appointment.doctor_registry(id,doctor_code,doctor_name,doctor_qualification,years_of_experience) values (?,?,?,?,?)";
		insertDoctorRegistryQuery = insertDoctorRegistryQuery.replaceFirst("\\?", "" + doctorRegistry.getId());
		insertDoctorRegistryQuery = insertDoctorRegistryQuery.replaceFirst("\\?", getQoutes(doctorRegistry.getDoctorCode()));
		insertDoctorRegistryQuery = insertDoctorRegistryQuery.replaceFirst("\\?", getQoutes(doctorRegistry.getDoctorName()));
		insertDoctorRegistryQuery = insertDoctorRegistryQuery.replaceFirst("\\?", getQoutes(doctorRegistry.getDoctorQualification()));
		insertDoctorRegistryQuery = insertDoctorRegistryQuery.replaceFirst("\\?", "" + doctorRegistry.getYearsOfExperience());
		System.out.println(insertDoctorRegistryQuery);
		int count = stmt.executeUpdate(insertDoctorRegistryQuery);
		System.out.println(count);
	}

	private String getQoutes(String input) {
		return "'" + input + "'";
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
		ResultSet rs = ((java.sql.Statement) stmt).executeQuery(getMaxId);
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
	public DoctorRegistry retrieve(DoctorRegistry doctorRegistry) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			retrieveDoctorRegistryData(stmt, doctorRegistry);
			retrieveDoctorRegistryDataByDoctorCode(stmt, doctorRegistry);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
		return null;
	}

	private void retrieveDoctorRegistryDataByDoctorCode(Statement stmt, DoctorRegistry doctorRegistry) throws SQLException {
		String retrieveDoctorRegistryQuery = "select * from doctor_appointment.doctor_registry where doctor_code=?";
		retrieveDoctorRegistryQuery = retrieveDoctorRegistryQuery.replaceFirst("\\?", getQoutes(doctorRegistry.getDoctorCode()));
		ResultSet rs = stmt.executeQuery(retrieveDoctorRegistryQuery);
		if (rs.next()) {
			doctorRegistry.setId(rs.getInt("id"));
			doctorRegistry.setDoctorCode(rs.getString("doctor_code"));
			doctorRegistry.setDoctorName(rs.getString("doctor_name"));
			doctorRegistry.setDoctorQualification(rs.getString("doctor_qualification"));
			doctorRegistry.setYearsOfExperience(rs.getInt("years_of_experience"));
		}
		System.out.println(doctorRegistry);
		close(rs);
	}

	private void retrieveDoctorRegistryData(Statement stmt, DoctorRegistry doctorRegistry) throws SQLException {
		String retrieveDoctorRegistryQuery = "select * from doctor_appointment.doctor_registry where id=?";
		retrieveDoctorRegistryQuery = retrieveDoctorRegistryQuery.replaceFirst("\\?", "" + doctorRegistry.getId());
		ResultSet rs = stmt.executeQuery(retrieveDoctorRegistryQuery);
		if (rs.next()) {
			doctorRegistry.setId(rs.getInt("id"));
			doctorRegistry.setDoctorCode(rs.getString("doctor_code"));
			doctorRegistry.setDoctorName(rs.getString("doctor_name"));
			doctorRegistry.setDoctorQualification(rs.getString("doctor_qualification"));
			doctorRegistry.setYearsOfExperience(rs.getInt("years_of_experience"));
		}
		System.out.println(doctorRegistry);
		close(rs);
	}

	@Override
	public DoctorRegistry delete(DoctorRegistry doctorRegistry) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			deleteDoctorRegistryDataById(stmt, doctorRegistry);
			deleteDoctorRegistryByDoctorCode(stmt, doctorRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
		return doctorRegistry;
	}

	private void deleteDoctorRegistryByDoctorCode(Statement stmt, DoctorRegistry doctorRegistry) throws SQLException {
		String deleteDoctorRegistryQuery = "delete from doctor_appointment.doctor_registry where doctor_code=?";
		deleteDoctorRegistryQuery = deleteDoctorRegistryQuery.replaceFirst("\\?", getQoutes(doctorRegistry.getDoctorCode()));
		System.out.println(deleteDoctorRegistryQuery);
		int count = stmt.executeUpdate(deleteDoctorRegistryQuery);
		System.out.println(count);
	}

	private void deleteDoctorRegistryDataById(Statement stmt, DoctorRegistry doctorRegistry) throws SQLException {
		String deleteDoctorRegistryQuery = "delete from doctor_appointment.doctor_registry where id=?";
		deleteDoctorRegistryQuery = deleteDoctorRegistryQuery.replaceFirst("\\?", "" + doctorRegistry.getId());
		System.out.println(deleteDoctorRegistryQuery);
		int count = stmt.executeUpdate(deleteDoctorRegistryQuery);
		System.out.println(count);

	}

	@Override
	public int retrieveDoctorIdByDoctorName(String doctorName) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		int id = 0;
		try {
			connection = getDBConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select id from doctor_appointment.doctor_registry where doctor_name='" + doctorName + "'");
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(connection);
		}
		return id;
	}

//	public static void main(String[] args) {
//		DoctorRegistry doctorRegistry = getDoctorRegistryObject();
//		// DoctorRegistry doctorRegistry = new DoctorRegistry();
//		// hospitalRegistry.setId(1);
//		DoctorRegistryDAOImpl doctorRegistryDAOImpl = new DoctorRegistryDAOImpl();
//		DoctorRegistry dbdoctorRegistry = doctorRegistryDAOImpl.save(getDoctorRegistryObject());
//		// System.out.println(dbhospitalRegistry);
//		System.out.println("Created Successfully...");
//
//
//         DoctorRegistry doctorRegistry1=getDoctorRegistryObject();
//         DoctorRegistryDAOImpl  doctorRegistryDAOImpl1=new DoctorRegistryDAOImpl();
//         DoctorRegistry dbDoctorRegistry=doctorRegistryDAOImpl1.getDoctorRegistryObject();
//	}
//
//	private static DoctorRegistry getDoctorRegistryObject() {
//		DoctorRegistry doctorRegistry = new DoctorRegistry();
//		// hospitalRegistry.setId(1);
//		doctorRegistry.setId(0);
//		doctorRegistry.setDoctorName("naveen");
//		doctorRegistry.setDoctorQualification("mbbs");
//		doctorRegistry.setYearsOfExperience(6);
//		return doctorRegistry;
//	}

}
