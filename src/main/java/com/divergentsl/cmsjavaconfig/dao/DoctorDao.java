package com.divergentsl.cmsjavaconfig.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Doctor Dao Class
 * 
 * @author Divergent
 *
 */
@Repository
public class DoctorDao {

	public static String ID = "did";
	public static String NAME = "dname";
	public static String SPECIALITY = "speciality";
	public static String CONTACT_NO = "dcontact";
	public static String FEE = "dfee";
	public static String DEGREE = "ddegree";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static Logger logger = LoggerFactory.getLogger(DoctorDao.class);

	/**
	 * Delete Method by String Id
	 * 
	 * @param string
	 * @return
	 * @throws SQLException
	 */
	public int delete(String string) throws SQLException {

		int st1 = jdbcTemplate.update("delete from doctor where d_id = '" + string + "'");
		return st1;
	}

	
	/**
	 * Search Record by that Method
	 * 
	 * @param did
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> searchById(String did) throws SQLException {
		List<Map<String, Object>> map = new ArrayList<>();
		map = jdbcTemplate.queryForList("select * from doctor where d_id ='" + did + "'");
		return map;
	}

	/**
	 * Insert Doctor Data By Insert Query And Parameter Below That
	 * 
	 * @param did
	 * @param dname
	 * @param speciality
	 * @param contactno
	 * @param fee
	 * @param degree
	 * @return
	 * @throws SQLException
	 */
	public int insert(String did, String dname, String speciality, String contactno, String fee, String degree)
			throws SQLException {
		String sql = "insert into doctor values(" + did + ", '" + dname + "','" + speciality + "'," + contactno + "','"+ fee + "','"+ degree +"')";
		int stmt = jdbcTemplate.update(sql);
		return stmt;
	}

	/**
	 * List Of All Doctor Data
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> list() throws SQLException {
		List<Map<String, Object>> doctorList = new ArrayList<>();
		doctorList = jdbcTemplate.queryForList("select * from doctor");
		return doctorList;
	}

}
