package com.divergentsl.cmsjavaconfig;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * In Which Get Url ,Username, Password Get
 * 
 * @author Divergent
 *
 */

public interface ClinicDatabase {
	

	final String URL = "spring.datasource.url";
	final String USERNAME = "spring.datasource.username";
	final String PASSWORD = "spring.datasource.password";
	
	public Connection getConnection() throws SQLException;
}
