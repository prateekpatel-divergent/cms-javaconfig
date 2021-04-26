package com.divergentsl.cmsjavaconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.PrescriptionAndNotesDao;
import com.divergentsl.cmsjavaconfig.dto.PrescriptionAndNotesDto;

/**
 * Prescription And Notes
 * 
 * @author Divergent
 *
 */
@Component
public class PrescriptionAndNotes {

	private static Logger logger = LoggerFactory.getLogger(PrescriptionAndNotes.class);

	@Autowired
	private PrescriptionAndNotesDao prescriptionAndNotesDao;

	/**
	 * Get Information
	 * 
	 * @return
	 */
	public Map<String, String> inputPrescriptionData() {
		Scanner sc = new Scanner(System.in);
		PrescriptionAndNotesDto prescriptionAndNotesDto = new PrescriptionAndNotesDto();
		System.out.println("Enter Prescription Id");
		String prescriId = sc.nextLine();
		prescriptionAndNotesDto.setPREID(prescriId);
		System.out.println("Enter Patient Id");
		String patientId = sc.nextLine();
		prescriptionAndNotesDto.setPID(patientId);
		System.out.println("Enter Prescription");
		String prescription = sc.nextLine();
		prescriptionAndNotesDto.setPRESCRIPTION(prescription);
		System.out.println("Enter Notes");
		String note = sc.nextLine();
		prescriptionAndNotesDto.setNOTE(note);
		System.out.println("Enter doctor id");
		String doctorid = sc.nextLine();
		prescriptionAndNotesDto.setDID(doctorid);

		Map<String, String> map = new HashMap<>();
		map.put("1", prescriId);
		map.put("2", patientId);
		map.put("3", prescription);
		map.put("4", note);
		map.put("5", doctorid);

		if (validatePrescriptionAndNotes(prescriptionAndNotesDto)) {
			return null;
		}
		return map;
	}

	/**
	 * Patient Prescription
	 */
	public void prescriptionPatient() {
		try {
			Map<String, String> map = inputPrescriptionData();
			if (map != null) {
				prescriptionAndNotesDao.insert(map.get("1"), map.get("2"), map.get("3"), map.get("4"), map.get("5"));
				logger.info("Insert Successfully");
			} else {
				logger.info("Invalid Input");
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}

	}
	
	/**
	 * validatorFactory method for validation
	 * @param prescriptionAndNotesDto
	 * @return
	 */
	private boolean validatePrescriptionAndNotes(PrescriptionAndNotesDto prescriptionAndNotesDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<PrescriptionAndNotesDto>> violations = validator.validate(prescriptionAndNotesDto);
		for (ConstraintViolation<PrescriptionAndNotesDto> violation : violations) {
			logger.error(violation.getMessage());
		}
		return violations.size() > 0;
	}

	/**
	 * History and Prescription of all Patient
	 */
	public static void historyAndPresciption() {
		logger.info("historyAndPresciption");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DataBaseManager.URL, DataBaseManager.USERNAME,
					DataBaseManager.PASSWORD);
			Statement st = con.createStatement();
			String sql = "select patient.p_id,patient.p_name,patient.age,patient.gender,appoinment.d_id,appoinment.d_name,\r\n"
					+ "appoinment.appoinment_id,appoinment.problem,appoinment.appoinment_date,\r\n"
					+ "prescription.dprescription,prescription.dnotes\r\n"
					+ "from patient inner join appoinment on patient.p_id =  appoinment.p_id   \r\n"
					+ "inner join prescription on patient.p_id = prescription.p_id\r\n"
					+ "order by appoinment.appoinment_date desc;";
			ResultSet rs = st.executeQuery(sql);
			logger.info("\n*-*-*-*-*-*-*-*-*-* History Of Patient *-*-*-*-*-*-*-*-*-*-*-*-*");
			logger.info(
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
			logger.info(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			st.close();
			con.close();

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

}