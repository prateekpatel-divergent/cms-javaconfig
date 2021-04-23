package com.divergentsl.cmsjavaconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.LabTestDao;

import java.sql.*;

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
			sc.close();
		}
	}

	/**
	 * Input Lab Test Data By Admin
	 * 
	 * @return
	 */
	public Map<String, String> inputLabTestData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter LabTest_Id");
		String lbid = sc.nextLine();
		System.out.println("Enter Patient Id");
		String pid = sc.nextLine();
		System.out.println("Enter Test");
		String testname = sc.nextLine();
		System.out.println("Enter Rate of Test");
		int rate = sc.nextInt();
		String ratestring = Integer.toString(rate);

		Map<String, String> map = new HashMap<>();
		map.put("1", lbid);
		map.put("2", pid);
		map.put("3", testname);
		map.put("4", ratestring);
		return map;
	}

	/**
	 * Insert Lab Test Data
	 */
	public void insertLabTestData() {
		try {
			labTestDao.insert("lbid", "pid", "testname", "currentdate", "5");
			logger.debug("Insert successfully!!!!!!");
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
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
								"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Drug Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");

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
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			List<Map<String, String>> labTestList = labTestDao.list();

			for (Map<String, String> labTest : labTestList) {
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
