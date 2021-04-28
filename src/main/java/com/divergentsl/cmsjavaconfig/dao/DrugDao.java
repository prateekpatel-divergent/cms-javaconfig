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
import org.springframework.jdbc.core.JdbcTemplate;
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
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static Logger logger = LoggerFactory.getLogger(DrugDao.class);

	/**
	 * Delete Method by Id
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(String id) throws SQLException {
		int st1 = jdbcTemplate.update("delete from drug where d_id = '" + id + "'");
		return st1;

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
		String sql = "insert into drug values('" + id + "', '" + name + "','" + rate + "')";
		int stmt = jdbcTemplate.update(sql);
		return stmt;
	}

	/**
	 * List Of All Record into DataBase
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> list() throws SQLException {
		List<Map<String, Object>> drugList = new ArrayList<>();
		drugList = jdbcTemplate.queryForList("select * from drug");
		return drugList;
	}

}
