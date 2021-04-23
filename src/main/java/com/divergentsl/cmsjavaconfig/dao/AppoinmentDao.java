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
import org.springframework.stereotype.Repository;

import com.divergentsl.cmsjavaconfig.ClinicDatabase;
import com.divergentsl.cmsjavaconfig.MainMenu;



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
	ClinicDatabase clinicDatabase;


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

		Connection con = null;

		con = clinicDatabase.getConnection();
		String sql = "insert into appoinment values(?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ID);
		stmt.setString(2, PNAME);
		stmt.setString(3, DNAME);
		stmt.setString(4, PROBLEM);
		stmt.setString(5, APPOINMENTDATE);
		long millis = System.currentTimeMillis();
		Date date1 = new Date(millis);
		stmt.setDate(6, date1);
		stmt.setString(7, DID);
		stmt.setString(8, PID);
		int i = stmt.executeUpdate();
		logger.info("\ninserted record successfully...");
		con.close();
		return i;
	}

	/**
	 * List All Patient That Have Appointment
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> list() throws SQLException {
		Connection con = null;
		Statement st = null;
		con = clinicDatabase.getConnection();
		st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from appoinment");
		List<Map<String, String>> appoinList = new ArrayList<>();
		while (rs.next()) {
			Map<String, String> appoinRecord = new HashMap<>();
			appoinRecord.put(ID, rs.getString(1));
			appoinRecord.put(PNAME, rs.getString(2));
			appoinRecord.put(DNAME, rs.getString(3));
			appoinRecord.put(PROBLEM, rs.getString(4));
			appoinRecord.put(APPOINMENTDATE, rs.getString(5));
			appoinRecord.put(CURRENTDATE, rs.getString(6));
			appoinRecord.put(PID, rs.getString(7));
			appoinRecord.put(DID, rs.getString(8));
			appoinList.add(appoinRecord);
		}
		return appoinList;
	}
}
