package com.divergentsl.cmsjavaconfig.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.divergentsl.cmsjavaconfig.DataBaseManager;

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
	private DataBaseManager dataBaseManager;

	private static Logger logger = LoggerFactory.getLogger(DoctorDao.class);
	
	/**
	 * Delete Method by String Id
	 * 
	 * @param string
	 * @return
	 * @throws SQLException
	 */
	public int delete(String string) throws SQLException {

		Connection con = dataBaseManager.getConnection();
		Statement st = con.createStatement();
		st = con.createStatement();
		int st1 = st.executeUpdate("delete from doctor where d_id = '" + string + "'");
		st.close();
		con.close();
		return st1;
	}

	/**
	 * Search Record by that Method
	 * 
	 * @param did
	 * @return
	 * @throws SQLException
	 */
	public Map searchById(String did) throws SQLException {
		Map<String, String> map = new HashMap<>();
		Connection con = dataBaseManager.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from doctor where d_id ='" + did + "'");

		if (rs.next()) {
			map.put(ID, rs.getString(1));
			map.put(NAME, rs.getString(2));
			map.put(SPECIALITY, rs.getString(3));
			map.put(CONTACT_NO, rs.getString(4));
			map.put(FEE, rs.getString(5));
			map.put(DEGREE, rs.getString(6));
			st.close();
			con.close();
		}
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
		Connection con = dataBaseManager.getConnection();
		String sql = "insert into doctor values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, did);
		stmt.setString(2, dname);
		stmt.setString(3, speciality);
		stmt.setString(4, contactno);
		stmt.setString(5, fee);
		stmt.setString(6, degree);
		int i = stmt.executeUpdate();
	
		con.close();
		return i;
	}

	/**
	 * List Of All Doctor Data
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> list() throws SQLException {
		Connection con = dataBaseManager.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from doctor");
		List<Map<String, String>> doctorList = new ArrayList<>();
		while (rs.next()) {
			Map<String, String> doctorRecord = new HashMap<>();
			doctorRecord.put(ID, rs.getString(1));
			doctorRecord.put(NAME, rs.getString(2));
			doctorRecord.put(SPECIALITY, rs.getString(3));
			doctorRecord.put(CONTACT_NO, rs.getString(4));
			doctorRecord.put(FEE, rs.getString(5));
			doctorRecord.put(DEGREE, rs.getString(6));
			doctorList.add(doctorRecord);
		}
		return doctorList;
	}

}
