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

/**
 * LabTest Class
 * 
 * @author Divergent
 *
 */
@Repository
public class LabTestDao {

	public static final String ID = "labid";
	public static final String PID = "pid";
	public static final String TEST = "test";
	public static final String TCURRENTDATE = "currentdate";
	public static final String RATE = "RATE";

	@Autowired
	ClinicDatabase clinicDatabase;

	private static Logger logger = LoggerFactory.getLogger(LabTestDao.class);

	/**
	 * Delete Record By ID
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(String id) throws SQLException {
		Connection con = null;
		Statement st = null;
		con = clinicDatabase.getConnection();
		st = con.createStatement();
		st.executeUpdate("delete from lab_test where PLab_id = '" + id + "'");
		st.close();
		con.close();
		return 1;

	}

	/**
	 * Search Record By ID
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map searchById(String id) throws SQLException {

		Connection con = null;
		Statement st = null;
		Map<String, String> map = new HashMap<>();

		con = clinicDatabase.getConnection();
		st = con.createStatement();

		ResultSet rs = st.executeQuery("select * from lab_test where plab_id = '" + id + "'");

		if (rs.next()) {
			map.put(ID, rs.getString(1));
			map.put(PID, rs.getString(2));
			map.put(TEST, rs.getString(3));
			map.put(TCURRENTDATE, rs.getString(4));
			map.put(RATE, rs.getString(5));
			st.close();
			con.close();
		}
		return map;
	}

	/**
	 * Insert Records Into Database
	 * 
	 * @param labid
	 * @param pid
	 * @param test
	 * @param date
	 * @param rate
	 * @return
	 * @throws SQLException
	 */
	public int insert(String labid, String pid, String test, String date, String rate) throws SQLException {

		Connection con = null;

		con = clinicDatabase.getConnection();
		String sql = "insert into lab_test values(?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ID);
		stmt.setString(2, PID);
		stmt.setString(3, TEST);
		long millis = System.currentTimeMillis();
		Date date1 = new Date(millis);
		stmt.setDate(4, date1);
		stmt.setString(5, RATE);
		int i = stmt.executeUpdate();
		logger.info("\ninserted record successfully...");
		con.close();
		return i;
	}

	/**
	 * List Of All Lab Test Record
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> list() throws SQLException {
		Connection con = null;
		Statement st = null;
		con = clinicDatabase.getConnection();
		st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from lab_test");
		List<Map<String, String>> labTestList = new ArrayList<>();
		while (rs.next()) {
			Map<String, String> map = new HashMap<>();
			map.put(ID, rs.getString(1));
			map.put(PID, rs.getString(2));
			map.put(TEST, rs.getString(3));
			map.put(TCURRENTDATE, rs.getString(4));
			map.put(RATE, rs.getString(5));
			labTestList.add(map);
		}
		return labTestList;
	}

}
//DTO - Data Tranfer Object