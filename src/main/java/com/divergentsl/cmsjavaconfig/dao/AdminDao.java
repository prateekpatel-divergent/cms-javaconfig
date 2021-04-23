package com.divergentsl.cmsjavaconfig.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.divergentsl.cmsjavaconfig.DataBaseManager;

import org.springframework.core.env.Environment;
/**
 * Admin Dao
 * 
 * @author Divergent
 *
 */
@Repository
public class AdminDao {

	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	@Autowired
	Environment evn;
	
	@Autowired
	private DataBaseManager dataBaseManager;
	/**
	 * Admin LOgin Method With Parameter
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean adminLogin(String username, String password) throws SQLException {

		
		Connection con = dataBaseManager.getConnection();
		Statement st = con.createStatement();


		ResultSet rs = st.executeQuery("select * from administration where a_username = '" + username
				+ "' AND a_password = '" + password + "'");

		if(rs.next())
			return true;
		else
			return false;
	}
}
