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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.divergentsl.cmsjavaconfig.DataBaseManager;

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
	private DataBaseManager dataBaseManager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(LabTestDao.class);

	/**
	 * Delete Record By ID
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(String id) throws SQLException {
		int st1 = jdbcTemplate.update("delete from lab_test where PLab_id = '" + id + "'");
		return st1;

	}

	/**
	 * Search Record By ID
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map searchById(String id) throws SQLException {

		Connection con = dataBaseManager.getConnection();
		Statement st = con.createStatement();
		Map<String, String> map = new HashMap<>();
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
	public int insert(String labid, String pid, String test,String date, String rate) throws SQLException {
		String sql = "insert into lab_test values('" + labid + "', '" + pid + "','" + test + "','" + date + "'," + rate + ")";
		int stmt = jdbcTemplate.update(sql);
		return stmt;
	}

	/**
	 * List Of All Lab Test Record
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> list() throws SQLException {
		List<Map<String, Object>> labTestList = new ArrayList<>();
		labTestList = jdbcTemplate.queryForList("select * from lab_test");
		return labTestList;
	}

}