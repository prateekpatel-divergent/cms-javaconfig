package com.divergentsl.cmsjavaconfig.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
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
	private JdbcTemplate jdbcTemplate;
	/**
	 * Admin LOgin Method With Parameter
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean adminLogin(String username, String password) throws SQLException {

		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from administration where a_username ='" + username + "' and a_password ='" + password + "'");

		if(list.isEmpty())
			return false;
		else
			return true;
	}
}
