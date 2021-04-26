package com.divergentsl.cmsjavaconfig;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.PatientDao;
import com.divergentsl.cmsjavaconfig.dto.DoctorDto;
import com.divergentsl.cmsjavaconfig.dto.PatientDto;

/**
 * All Patient CRUD Operation Is done
 * 
 * @author Divergent
 *
 */
@Component
public class Patient {

	private static Logger logger = LoggerFactory.getLogger(Patient.class);

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private Admin admin;

	@Autowired
	private Doctor doctor;

	/**
	 * Show All OPtion Of Admin to CRUD Operation
	 */
	public void showAll() {
		System.out.println(
				"1. Insert Patient Data \n  2. Update Patient Data \n  3. Search Patient Data \n  4. Delete Patient Data \n  5. List All Patient \n  6. Back");
	}

	/**
	 * Select Option by this method
	 */
	public void patientPanel() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			showAll();
			String input = sc.nextLine();
			switch (input) {
			case "1":
				insertPatientData();
				break;
			case "2":
//				updatePatientData();
				break;
			case "3":
				searchPatientData();
				break;
			case "4":
				deletePatientData(null);
				break;
			case "5":
				listAllPatientData();
				break;
			case "6":
				admin.executedMethod();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Insert Patient Data into Map
	 * 
	 * @return
	 */
	public Map<String, String> inputPatientData() {
		Scanner sc = new Scanner(System.in);
		PatientDto patientDto = new PatientDto();
		System.out.println("Enter Patient_ID,Name,Address,Age,Weight,Gender,Contact_No,AppoimentDate,Problem");
		String pid = sc.nextLine();
		patientDto.setID(pid);
		String pname = sc.nextLine();
		patientDto.setPNAME(pname);
		String paddress = sc.nextLine();
		patientDto.setADDRESS(paddress);
		String page = sc.nextLine();
		patientDto.setAGE(page);
		String pweight = sc.nextLine();
		patientDto.setWEIGHT(pweight);
		String pgender = sc.nextLine();
		patientDto.setGENDER(pgender);
		String pcontactno = sc.nextLine();
		patientDto.setCONTACTNO(pcontactno);
		String pappoimentdate = sc.nextLine();
		patientDto.setAPPOINTMENTDATE(pappoimentdate);
		String pproblem = sc.nextLine();
		patientDto.setPROBLEM(pproblem);

		Map<String, String> map = new HashMap<>();
		map.put("1", pid);
		map.put("2", pname);
		map.put("3", paddress);
		map.put("4", page);
		map.put("5", pweight);
		map.put("6", pgender);
		map.put("7", pcontactno);
		map.put("9", pappoimentdate);
		map.put("10", pproblem);

		if (validatePatient(patientDto)) {
			return null;
		}
		return map;
	}

	/**
	 * In Get Map Data above Method and Store by Insert Query
	 */
	public void insertPatientData() {
		try {
			Map<String, String> map = inputPatientData();
			if (map != null) {
				patientDao.insert(map.get("1"), map.get("2"), map.get("3"), map.get("4"), map.get("5"), map.get("6"),
						map.get("7"), map.get("curdate"), map.get("9"), map.get("10"));
				logger.info("Insert data Succesfully");
			} else {
				logger.info("Invalid Input");
			}
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}

	/**
	 * validatorFactory method for validation
	 * @param patientDto
	 * @return
	 */
	private boolean validatePatient(PatientDto patientDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<PatientDto>> violations = validator.validate(patientDto);
		for (ConstraintViolation<PatientDto> violation : violations) {
			logger.error(violation.getMessage());
		}
		return violations.size() > 0;
	}

	/**
	 * Patient Data Update
	 */
	public static void updatePatientData(String pproblem, String pid) {
		try {
			String sql = "UPDATE patient SET problem = '" + pproblem + "' where p_id= '" + pid + "';";
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Search Patient Data By Id
	 */
	public void searchPatientData() {
		System.out.println("\nEnter Patient Id :");
		Scanner sc = new Scanner(System.in);
		String did = sc.nextLine();

		try {

			Map<String, String> patient = patientDao.searchById(did);

			if (patient.size() != 0) {
				System.out.println(
						"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
				System.out.printf("%5s  %15s  %15s  %3s  %4s  %6s  %12s  %12s  %12s  %20s\n",
						patient.get(PatientDao.ID), patient.get(PatientDao.PNAME), patient.get(PatientDao.ADDRESS),
						patient.get(PatientDao.AGE), patient.get(PatientDao.WEIGHT), patient.get(PatientDao.GENDER),
						patient.get(PatientDao.CONTACTNO), patient.get(PatientDao.ACURRENTDATE),
						patient.get(PatientDao.APPOINTMENTDATE), patient.get(PatientDao.PROBLEM));
				System.out.println(
						"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			} else {
				logger.info("Record is not Found");
			}

		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}

	/**
	 * Delete Patient Data
	 */
	public void deletePatientData(String pid) {
		System.out.println("\nEnter Patient Id :");
		Scanner sc = new Scanner(System.in);
		String did = sc.nextLine();

		try {
			if (patientDao.searchById(did).size() == 0) {
				logger.info("\nPatient not found!");
			} else {
				try {
					patientDao.delete(did);
					logger.info("\nRecord Deleted Successfully...");
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Show All Patient Data
	 */
	public void listAllPatientData() {
		try {
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			List<Map<String, String>> patientList = patientDao.list();

			for (Map<String, String> patient : patientList) {
				System.out.printf("%5s  %15s  %15s  %3s  %4s  %6s  %12s  %12s  %12s  %20s\n",
						patient.get(PatientDao.ID), patient.get(PatientDao.PNAME), patient.get(PatientDao.ADDRESS),
						patient.get(PatientDao.AGE), patient.get(PatientDao.WEIGHT), patient.get(PatientDao.GENDER),
						patient.get(PatientDao.CONTACTNO), patient.get(PatientDao.ACURRENTDATE),
						patient.get(PatientDao.APPOINTMENTDATE), patient.get(PatientDao.PROBLEM));
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");

		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Generate Invoice Of Patient
	 */
	public void generateInvoice() {
		logger.info("Generate Invoice");
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(ClinicDatabase.URL, ClinicDatabase.USERNAME,
					ClinicDatabase.PASSWORD);
			Statement st = con.createStatement();
			logger.info("\nEnter Patient Id");
			String id = sc.nextLine();
			String sql = "select appoinment.P_ID,appoinment.P_Name,appoinment.ACurrent_Date,appoinment.Problem,doctor.D_Name,doctor.fee\r\n"
					+ "from appoinment join doctor on appoinment.d_id = doctor.D_Id where appoinment.p_id ='" + id
					+ "';";
			ResultSet rs = st.executeQuery(sql);

			if (!rs.next()) {
				logger.info("Record Is not Found!\n");
				doctor.printDoctorOptions();
			} else {
				System.out.println(
						"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Patient Invoice*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
				String pid = rs.getString(1);
				String pname = rs.getString(2);
				Date pcurrentdate = rs.getDate(3);
				String pproblem = rs.getString(4);
				String dname = rs.getString(5);
				String fee = rs.getString(6);
				System.out.printf("%30s  %30s\n", "Patient Id :" + pid, "Date : " + pcurrentdate);
				System.out.printf("%30s %30s\n", "Patient Name :" + pname, "Problem :" + pproblem);
				System.out.printf("%30s %30s\n\n", "Docter Name :" + dname, "Fee :" + fee);
				System.out.println(
						"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			}
			doctor.printDoctorOptions();
			st.close();
			con.close();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

}
