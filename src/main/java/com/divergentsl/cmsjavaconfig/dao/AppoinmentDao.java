package com.divergentsl.cmsjavaconfig.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.divergentsl.cmsjavaconfig.DataBaseManager;



/**
 * Appointment Dao Class
 * 
 * @author Divergent
 *
 */
@Repository
public class AppoinmentDao {

	public static final String ID = "appoinID";
	public static final String PNAME = "pName";
	public static final String DNAME = "dName";
	public static final String PROBLEM = "problem";
	public static final String APPOINMENTDATE = "appoinDate";
	public static final String CURRENTDATE = "currentDate";
	public static final String DID = "dId";
	public static final String PID = "pId";

	private static Logger logger = LoggerFactory.getLogger(AppoinmentDao.class);
	
	@Autowired
	Environment evn;
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;


	/**
	 * Insert Patient Record Method with Parameter
	 * 
	 * @param appinId
	 * @param pName
	 * @param dName
	 * @param problem
	 * @param appoinmentDate
	 * @param date
	 * @param pId
	 * @param dId
	 * @return
	 * @throws SQLException
	 */
	public int insert(String appinId, String pName, String dName, String problem, String appoinmentDate, String date,
			String pId, String dId) throws SQLException {
	
		logger.info("\ninserted record successfully...");
		return this.jdbcTemplate.update("INSERT INTO appointment values('" + appinId + "','" + pName + "','"
				+ dName + "','" + problem + "','" + appoinmentDate + "','" + date + "','" + pId + "','" + dId + "')");
	}

	/**
	 * List All Patient That Have Appointment
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> list() throws SQLException {
		
		List<Map<String, Object>> appoinList = new ArrayList<>();
		appoinList = jdbcTemplate.queryForList("select * from appoinment");
		return appoinList;
	}
}
