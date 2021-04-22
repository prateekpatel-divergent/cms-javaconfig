package com.divergentsl.cmsjavaconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.PrescriptionAndNotesDao;


/**
 * Prescription And Notes
 * 
 * @author Divergent
 *
 */
@Component
public class PrescriptionAndNotes {
	
	private static Logger logger = LoggerFactory.getLogger(PrescriptionAndNotes.class);
	
	public PrescriptionAndNotesDao prescriptionAndNotesDao;

	/**
	 * Get Information
	 * 
	 * @return
	 */
	public Map<String, String> inputPrescriptionData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Prescription Id");
		String prescriId = sc.nextLine();
		System.out.println("Enter Patient Id");
		String patientId = sc.nextLine();
		System.out.println("Enter Prescription");
		String prescription = sc.nextLine();
		System.out.println("Enter Notes");
		String note = sc.nextLine();
		System.out.println("Enter doctor id");
		String doctorid = sc.nextLine();

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", prescriId);
		map.put("2", patientId);
		map.put("3", prescription);
		map.put("4", note);
		map.put("5", doctorid);
		return map;
	}

	/**
	 * Patient Prescription
	 */
	public void prescriptionPatient() {
		try {
			prescriptionAndNotesDao.insert("prescriId", "patientId", "prescription", "note", "doctorid");
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}

	}

	/**
	 * History and Prescription of all Patient
	 */
	public static void historyAndPresciption() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			String sql = "select patient.p_id,patient.p_name,patient.age,patient.gender,appoinment.d_id,appoinment.d_name,\r\n"
					+ "appoinment.appoinment_id,appoinment.problem,appoinment.appoinment_date,\r\n"
					+ "prescription.dprescription,prescription.dnotes\r\n"
					+ "from patient inner join appoinment on patient.p_id =  appoinment.p_id   \r\n"
					+ "inner join prescription on patient.p_id = prescription.p_id\r\n"
					+ "order by appoinment.appoinment_date desc;";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("\n*-*-*-*-*-*-*-*-*-* History Of Patient *-*-*-*-*-*-*-*-*-*-*-*-*");
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Patient Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			while (rs.next()) {
				String pid = rs.getString(1);
				String pname = rs.getString(2);
				String page = rs.getString(3);
				String pgender = rs.getString(4);
				String doctorId = rs.getString(5);
				String doctorname = rs.getString(6);
				String appoinId = rs.getString(7);
				String pproblem = rs.getString(8);
				String appoindate = rs.getString(9);
				String prescription = rs.getString(10);
				String note = rs.getString(11);
				System.out.printf("%5s  %20s  %5s  %7s  %5s  %20s  %3s  %15s  %15s  %25s  %25s\n", pid, pname, page,
						pgender, doctorId, doctorname, appoinId, pproblem, appoindate, prescription, note);
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}

}