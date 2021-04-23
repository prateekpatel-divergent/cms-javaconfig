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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.divergentsl.cmsjavaconfig.DataBaseManager;


/**
 * Drug Class
 * 
 * @author Divergent
 *
 */
@Repository
public class DrugDao {

	public static final String ID = "did";
	public static final String NAME = "dname";
	public static final String RATE = "rate";

	@Autowired
	private DataBaseManager dataBaseManager;

	private static Logger logger = LoggerFactory.getLogger(DrugDao.class);

	/**
	 * Delete Method by Id
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(String id) throws SQLException {
		Connection con = dataBaseManager.getConnection();
		Statement st = con.createStatement();
		st.executeUpdate("delete from drug where d_id = '" + id + "'");
		st.close();
		con.close();
		return 1;

	}

	/**
	 * Search Record By Id
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map searchById(String id) throws SQLException {
		Connection con = dataBaseManager.getConnection();
		Statement st = con.createStatement();
		Map<String, String> map = new HashMap<>();

		ResultSet rs = st.executeQuery("select * from drug where d_id = '" + id + "'");

		if (rs.next()) {
			map.put(ID, rs.getString(1));
			map.put(NAME, rs.getString(2));
			map.put(RATE, rs.getString(3));
			st.close();
			con.close();
		}
		return map;
	}

	/**
	 * Insert Record Into Data Base By Insert Data
	 * 
	 * @param id
	 * @param name
	 * @param rate
	 * @return
	 * @throws SQLException
	 */
	public int insert(String id, String name, String rate) throws SQLException {
		Connection con = dataBaseManager.getConnection();
		String sql = "insert into drug values(?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ID);
		stmt.setString(2, NAME);
		stmt.setString(3, RATE);
		int i = stmt.executeUpdate();
		logger.info("\ninserted record successfully...");
		con.close();
		return i;
	}

	/**
	 * List Of All Record into DataBase
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> list() throws SQLException {
		Connection con = dataBaseManager.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from drug");
		List<Map<String, String>> durgList = new ArrayList<>();
		while (rs.next()) {
			Map<String, String> map = new HashMap<>();
			map.put(ID, rs.getString(1));
			map.put(NAME, rs.getString(2));
			map.put(RATE, rs.getString(3));
			durgList.add(map);
		}
		return durgList;
	}

}
