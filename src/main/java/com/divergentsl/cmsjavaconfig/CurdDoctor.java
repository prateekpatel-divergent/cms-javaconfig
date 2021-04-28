package com.divergentsl.cmsjavaconfig;

import java.sql.*;
import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.DoctorDao;
import com.divergentsl.cmsjavaconfig.dto.DoctorDto;

@Component
public class CurdDoctor {

	private static Logger logger = LoggerFactory.getLogger(CurdDoctor.class);

	@Autowired
	private DoctorDao doctorDao;

	@Autowired
	private Admin admin;

	/**
	 * It show Option on console
	 */
	public static void showCRUDDoctor() {
		System.out.println("1. Insert Doctor Data");
		System.out.println("2. Update Doctor Data");
		System.out.println("3. Search Doctor Data");
		System.out.println("4. Delete Doctor Data");
		System.out.println("5. List All Doctor");
		System.out.println("6. Back");
	}

	/**
	 * It Select Option on Console Panel to choice on It.
	 */
	public void docterPanel() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Your Choice : ");

			showCRUDDoctor();
			String input = sc.nextLine();
			switch (input) {
			case "1":
				insertDoctorData();
				break;
			case "2":
				break;
			case "3":
				searchDoctorData();
				break;
			case "4":
				deleteDoctorData();
				break;
			case "5":
				listAllDoctor();
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
	 * Get Data By Admit for insert data
	 * 
	 * @return
	 */
	public Map<String, String> inputDoctorData() {

		Scanner sc = new Scanner(System.in);
		DoctorDto doctorDto = new DoctorDto();
		System.out.println("Enter Doctor_Id");
		String did = sc.nextLine();
		doctorDto.setId(did);
		System.out.println("Enter Doctor Name");
		String dname = sc.nextLine();
		doctorDto.setName(dname);
		System.out.println("Enter Speciaslity");
		String dspecia = sc.nextLine();
		doctorDto.setSpeciality(dspecia);
		System.out.println("Enter ContactNo");
		String dcontact = sc.nextLine();
		doctorDto.setContactno(dcontact);
		System.out.println("Enter Fee");
		String dfee = sc.nextLine();
		doctorDto.setFee(dfee);
		System.out.println("Enter Degree");
		String ddegree = sc.nextLine();
		doctorDto.setDegree(ddegree);

		Map<String, String> map = new HashMap<>();
		map.put("1", did);
		map.put("2", dname);
		map.put("3", dspecia);
		map.put("4", dcontact);
		map.put("5", dfee);
		map.put("6", ddegree);

		if (validateDoctor(doctorDto)) {
			return null;
		}
		return map;
	}

	/**
	 * Insert Doctor Data
	 */
	public void insertDoctorData() {
		try {
			Map<String, String> map = inputDoctorData();
			if (map != null) {
				doctorDao.insert(map.get("1"), map.get("2"), map.get("3"), map.get("4"), map.get("5"), map.get("6"));
				logger.info("\ninserted record successfully...");
			} else {
				logger.info("Invalid Input");
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * validatorFactory method for validation
	 * 
	 * @param doctorDto
	 * @return
	 */
	private boolean validateDoctor(DoctorDto doctorDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<DoctorDto>> violations = validator.validate(doctorDto);
		for (ConstraintViolation<DoctorDto> violation : violations) {
			logger.error(violation.getMessage());
		}
		return violations.size() > 0;
	}

	/**
	 * Search Doctor Data admin
	 */
	public void searchDoctorData() {

		System.out.println("\nEnter Doctor Id :");
		Scanner sc = new Scanner(System.in);
		String id = sc.nextLine();

		try {
			if (doctorDao.searchById(id).size() == 0) {
				logger.info("\nDoctor not found!");
			} else {

				try {
					List<Map<String, Object>> aDoctor = doctorDao.searchById(id);
					if (aDoctor.size() != 0) {
						System.out.println(
								"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
						System.out.printf("%10s  %15s  %15s  %15s  %10s  %20s\n", aDoctor.get(0), aDoctor.get(1),
								aDoctor.get(2), aDoctor.get(3), aDoctor.get(4), aDoctor.get(5));
						System.out.println(
								"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
					} else {
						logger.info("Record is not found");
					}
					logger.debug("\nRecord Find Successfully...");
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Delete Doctor Data by Id
	 */
	public void deleteDoctorData() {
		System.out.println("\nEnter Doctor Id :");
		Scanner sc = new Scanner(System.in);
		String did = sc.nextLine();

		try {
			if (doctorDao.searchById(did).size() == 0) {
				logger.info("\nDoctor not found!");
			} else {
				try {
					doctorDao.delete(did);
					logger.info("\nRecord Deleted Successfully...");
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Show All List Of Doctor
	 */
	public String listAllDoctor() {

		try {
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			List<Map<String, Object>> doctorList = doctorDao.list();
			for (Map<String, Object> aDoctor : doctorList) {
				System.out.printf("%10s  %15s  %15s  %15s  %10s  %20s\n", aDoctor.get(doctorDao.ID),
						aDoctor.get(doctorDao.NAME), aDoctor.get(doctorDao.SPECIALITY),
						aDoctor.get(doctorDao.CONTACT_NO), aDoctor.get(doctorDao.FEE), aDoctor.get(doctorDao.DEGREE));
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return null;
	}

}
