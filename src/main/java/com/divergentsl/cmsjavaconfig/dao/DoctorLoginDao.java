package com.divergentsl.cmsjavaconfig.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.divergentsl.cmsjavaconfig.ClinicDatabase;

/**
 * Doctor Login Class
 * 
 * @author Divergent
 *
 */
@Repository
public class DoctorLoginDao {
	
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	@Autowired
	ClinicDatabase clinicDatabase;


	/**
	 * Doctor Login Method By Parameter
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean doctorLogin(String username, String password) throws SQLException {

		Connection con = null;
		Statement st = null;

		con = clinicDatabase.getConnection();
		st = con.createStatement();

		ResultSet rs = st.executeQuery("select * from administration where a_username = '" + username
				+ "' AND a_password = '" + password + "'");

		if (rs.next())
			return true;
		else
			return false;
	}
}
