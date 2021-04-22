package com.divergentsl.cmsjavaconfig;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.DrugDao;

/**
 * Drug Class For All CRUD Operation
 * 
 * @author Divergent
 *
 */
@Component
public class Drug {

	private static Logger logger = LoggerFactory.getLogger(Drug.class);
	
	@Autowired
	public DrugDao drugDao;

	@Autowired
	public Admin admin;
	/**
	 * Show All Option
	 */
	public static void showCRUDDrug() {
		System.out.println(
				"1. Insert Drug Data \n  2. Update Drug Data \n  3. Search Drug Data \n  4. Delete Drug Data \n  5. List All Drug \n  6. Back");
	}

	/**
	 * Get all Panel Option
	 */
	public void drugPanel() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Your Choice : ");
			showCRUDDrug();
			String input = sc.nextLine();
			switch (input) {
			case "1":
				insertDrugData();
				break;
			case "2":
				break;
			case "3":
				searchDrugData();
				break;
			case "4":
				deleteDrugData();
				break;
			case "5":
				listAllDrug();
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
	 * It get Drug Infomation
	 * 
	 * @return
	 */
	public Map<String, String> inputDoctorData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Drug_Id");
		String did = sc.nextLine();
		System.out.println("Enter Drug Name");
		String dname = sc.nextLine();
		System.out.println("Enter Drug Rate");
		String drate = sc.nextLine();

		Map<String, String> map = new HashMap<>();
		map.put("1", did);
		map.put("2", dname);
		map.put("3", drate);
		return map;
	}

	/**
	 * Insert all Drug Data
	 */
	public void insertDrugData() {
		try {
			Map<String, String> map = inputDoctorData();
			map.get("id");
			map.get("name");
			map.get("rate");
			drugDao.insert("id", "name", "rate");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Search Drug Data
	 */
	public void searchDrugData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Drug Id : ");
		String id = sc.nextLine();
		try {
			if (drugDao.searchById(id).size() == 0) {
				logger.debug("\n Record is Not Found");
			} else {
				try {
					Map<String, String> drug = drugDao.searchById(id);
					if (drug.size() != 0) {
						System.out.println(
								"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Drug Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");

						System.out.printf("%5s  %20s  %25s  \n", drug.get(DrugDao.ID), drug.get(DrugDao.NAME),
								drug.get(DrugDao.RATE));
						System.out.println(
								"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
					}
				} catch (SQLException e) {
					logger.debug(e.getMessage());
				}
			}
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}

	}

	/**
	 * Delete Drug Data
	 */
	public void deleteDrugData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Drug Id : ");
		String id = sc.nextLine();

		try {
			if (drugDao.searchById(id).size() == 0) {
				logger.debug("\nDrug not found!");
			} else {
				try {
					drugDao.delete(id);
					logger.debug("\nRecord Deleted Successfully...");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}

	/**
	 * Show All Drug List
	 */
	public void listAllDrug() {
		try {
			List<Map<String, String>> doctorList = drugDao.list();
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			for (Map<String, String> drug : doctorList) {
				System.out.printf("%5s  %20s  %25s  \n", drug.get(DrugDao.ID), drug.get(DrugDao.NAME),
						drug.get(DrugDao.RATE));
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
}
