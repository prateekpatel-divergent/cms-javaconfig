package com.divergentsl.cmsjavaconfig.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.divergentsl.cmsjavaconfig.DataBaseManager;

/**
 * Prescription And Notes Class
 * 
 * @author Divergent
 *
 */
@Repository
public class PrescriptionAndNotesDao {

	public static final String PREID = "pre_ID";
	public static final String PID = "P_ID";
	public static final String PRESCRIPTION = "dprescription";
	public static final String NOTE = "dnotes";
	public static final String DID = "d_id";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static Logger logger = LoggerFactory.getLogger(PrescriptionAndNotesDao.class);

	/**
	 * Insert Record Into Prescription and Notes
	 * 
	 * @param preId
	 * @param pId
	 * @param prescription
	 * @param note
	 * @param dId
	 * @return
	 * @throws SQLException
	 */
	public int insert(String preId, String pId, String prescription, String note, String dId) throws SQLException {
		String sql = "insert into doctor values(" + preId + ", '" + pId + "','" + prescription + "'," + note + "','"+ dId + "')";
		int stmt = jdbcTemplate.update(sql);
		return stmt;
	}

}
