package com.divergentsl.cmsjavaconfig.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.divergentsl.cmsjavaconfig.ClinicDatabase;


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
	ClinicDatabase clinicDatabase;

	
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
		Connection con = null;
		con = clinicDatabase.getConnection();
		String sql = "insert into prescription values(?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, preId);
		stmt.setString(2, pId);
		stmt.setString(3, prescription);
		stmt.setString(4, note);
		stmt.setString(5, dId);
		int i = stmt.executeUpdate();
		logger.info("\ninserted record successfully...");
		con.close();
		return i;
	}

}
