package com.divergentsl.cmsjavaconfig.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.divergentsl.cmsjavaconfig.DataBaseManager;

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
	private JdbcTemplate jdbcTemplate;

	/**
	 * Doctor Login Method By Parameter
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean doctorLogin(String username, String password) throws SQLException {

		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from administration where a_username = '" + username
				+ "' AND a_password = '" + password + "'");

		if (list.isEmpty())
			return false;
		else
			return true;
	}
}
