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
 * Patient Class
 * 
 * @author Divergent
 *
 */
@Repository
public class PatientDao {

	public static final String ID = "p_id";
	public static final String PNAME = "p_name";
	public static final String ADDRESS = "address";
	public static final String AGE = "age";
	public static final String WEIGHT = "weight";
	public static final String GENDER = "gender";
	public static final String CONTACTNO = "contactno";
	public static final String ACURRENTDATE = "acurrentdate";
	public static final String APPOINTMENTDATE = "appointmentdate";
	public static final String PROBLEM = "problem";

	private static Logger logger = LoggerFactory.getLogger(PatientDao.class);

	@Autowired
	private DataBaseManager dataBaseManager;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Delete Record By ID
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public int delete(String pid) throws SQLException {
		int st1 = jdbcTemplate.update("delete from delete where d_id = '" + pid + "'");
		return st1;

	}

	/**
	 * Search Record By ID
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public Map searchById(String pid) throws SQLException {

		Connection con = dataBaseManager.getConnection();
		Statement st = con.createStatement();
		Map<String, String> map = new HashMap<>();
		ResultSet rs = st.executeQuery("select * from patient where p_id = '" + pid + "'");

		if (rs.next()) {
			map.put(ID, rs.getString(1));
			map.put(PNAME, rs.getString(2));
			map.put(ADDRESS, rs.getString(3));
			map.put(AGE, rs.getString(4));
			map.put(WEIGHT, rs.getString(5));
			map.put(GENDER, rs.getString(6));
			map.put(CONTACTNO, rs.getString(7));
			map.put(ACURRENTDATE, rs.getString(8));
			map.put(APPOINTMENTDATE, rs.getString(9));
			map.put(PROBLEM, rs.getString(10));
			st.close();
			con.close();
		}
		return map;
	}

	/**
	 * Insert Record Into Database By Parameter
	 * 
	 * @param pid
	 * @param pname
	 * @param address
	 * @param age
	 * @param weight
	 * @param gender
	 * @param contactno
	 * @param curdate
	 * @param appoinmentdate
	 * @param problem
	 * @return
	 * @throws SQLException
	 */
	public int insert(String pid, String pname, String address, String age, String weight, String gender,
			String contactno, String curdate, String appoinmentdate, String problem) throws SQLException {
		String sql = "insert into patient values('" + pid + "', '" + pname + "','" + address + "','" + age + "','"
				+ weight + "','" + gender + "','" + contactno + "','" + curdate + "','" + appoinmentdate + "','"
				+ problem + "')";
		int stmt = jdbcTemplate.update(sql);
		return stmt;
	}

	/**
	 * List of all Patient
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> list() throws SQLException {

		List<Map<String, Object>> patientList = new ArrayList<>();
		patientList = jdbcTemplate.queryForList("select * from patient");
		return patientList;
	}

}
