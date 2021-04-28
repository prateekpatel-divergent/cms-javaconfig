package com.divergentsl.cmsjavaconfig;

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

import com.divergentsl.cmsjavaconfig.dao.LabTestDao;
import com.divergentsl.cmsjavaconfig.dto.DrugDto;
import com.divergentsl.cmsjavaconfig.dto.LabTestDto;

import java.sql.*;
import java.time.LocalDate;

/**
 * All Opeartion Of CRUD Lab Test
 * 
 * @author Divergent
 *
 */
@Component
public class LabTest {

	private static Logger logger = LoggerFactory.getLogger(LabTest.class);

	@Autowired
	private LabTestDao labTestDao;

	@Autowired
	private Admin admin;

	/**
	 * Show All Operation on Console
	 */
	public void showCRUDDrug() {
		System.out.println(
				"1. Insert Lab Test Data \n  2. Update Lab Test Data \n  3. Search Lab Test Data \n  4. Delete Lab Test Data \n  5. List All Lab Test \n  6. Back");
	}

	/**
	 * Lab Test Panel
	 * 
	 * @throws SQLException
	 */
	public void labTestPanel() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Your Choice : ");

			showCRUDDrug();
			String input = sc.nextLine();
			switch (input) {
			case "1":
				insertLabTestData();
				break;
			case "2":
				updateLabTestDoctor();
				break;
			case "3":
				searchLabTestData();
				break;
			case "4":
				deleteLabTestData();
				break;
			case "5":
				listAllLabTest();
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
	 * Input Lab Test Data By Admin
	 * 
	 * @return
	 */
	public Map<String, String> inputLabTestData() {
		Scanner sc = new Scanner(System.in);
		LabTestDto labTestDto = new LabTestDto();
		System.out.println("Enter LabTest_Id");
		String lbid = sc.nextLine();
		labTestDto.setID(lbid);
		System.out.println("Enter Patient Id");
		String pid = sc.nextLine();
		labTestDto.setPID(pid);
		System.out.println("Enter Test");
		String testname = sc.nextLine();
		labTestDto.setTEST(testname);
		System.out.println("Enter Rate of Test");
		int rate = sc.nextInt();
		String ratestring = Integer.toString(rate);
		labTestDto.setRATE(ratestring);

		Map<String, String> map = new HashMap<>();
		map.put("1", lbid);
		map.put("2", pid);
		map.put("3", testname);
		map.put("4", ratestring);

		if (validateLabTest(labTestDto)) {
			return null;
		}
		return map;
	}

	/**
	 * Insert Lab Test Data
	 */
	public void insertLabTestData() {
		try {
			Map<String, String> map = inputLabTestData();
			if (map != null) {
				labTestDao.insert(map.get("1"), map.get("2"), map.get("3"), map.get("null"), map.get("4"));
				logger.debug("Insert successfully!!!!!!");
			} else {
				logger.info("Invalid Input");
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * validatorFactory method for validation
	 * @param labTestDto
	 * @return
	 */
	private boolean validateLabTest(LabTestDto labTestDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<LabTestDto>> violations = validator.validate(labTestDto);
		for (ConstraintViolation<LabTestDto> violation : violations) {
			logger.error(violation.getMessage());
		}
		return violations.size() > 0;
	}

	/**
	 * Update Lab Test Data
	 */
	public static void updateLabTestDoctor() {
		System.out.println("Not Complete");
	}

	/**
	 * Search Lab Test Data By Id
	 */
	public void searchLabTestData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Lab Test Id : ");
		String id = sc.nextLine();

		try {
			if (labTestDao.searchById(id).size() == 0) {
				logger.info("\n Record is Not Found");
			} else {
				try {
					Map<String, String> labTest = labTestDao.searchById(id);
					if (labTest.size() != 0) {
						System.out.println(
								"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*LabTest Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");

						System.out.printf("%5s  %5s  %20s  %15s  %4s\n", labTest.get(LabTestDao.ID),
								labTest.get(LabTestDao.PID), labTest.get(LabTestDao.TEST),
								labTest.get(LabTestDao.TCURRENTDATE), labTest.get(LabTestDao.RATE));
						System.out.println(
								"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
					}
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Delete Lab Test Data By Id
	 */
	public void deleteLabTestData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Lab Test Id : ");
		String id = sc.nextLine();

		try {
			if (labTestDao.searchById(id).size() == 0) {
				logger.info("\nTest is not found!");
			} else {
				try {
					labTestDao.delete(id);
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
	 * List And Show All Lab Test Data
	 */
	public void listAllLabTest() {
		try {
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*LabTest Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			List<Map<String, Object>> labTestList = labTestDao.list();

			for (Map<String, Object> labTest : labTestList) {
				System.out.printf("%5s  %5s  %20s  %15s  %4s\n", labTest.get(LabTestDao.ID),
						labTest.get(LabTestDao.PID), labTest.get(LabTestDao.TEST), labTest.get(LabTestDao.TCURRENTDATE),
						labTest.get(LabTestDao.RATE));
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}
}
