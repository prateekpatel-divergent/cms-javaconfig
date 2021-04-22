package com.divergentsl.cmsjavaconfig;

import java.sql.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.DoctorDao;


@Component
public class CurdDoctor {

	static Scanner sc = new Scanner(System.in);
	private static Logger logger = LoggerFactory.getLogger(CurdDoctor.class);

	@Autowired
	public DoctorDao doctorDao;
	
	@Autowired
	public Admin admin;

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
		System.out.println("Enter Doctor_Id");
		String did = sc.nextLine();
		System.out.println("Enter Doctor Name");
		String dname = sc.nextLine();
		System.out.println("Enter Speciaslity");
		String dspecia = sc.nextLine();
		System.out.println("Enter ContactNo");
		String dcontact = sc.nextLine();
		System.out.println("Enter Fee");
		String dfee = sc.nextLine();
		System.out.println("Enter Degree");
		String ddegree = sc.nextLine();

		Map<String, String> map = new HashMap<>();
		map.put("1", did);
		map.put("2", dname);
		map.put("3", dspecia);
		map.put("4", dcontact);
		map.put("5", dfee);
		map.put("6", ddegree);
		return map;
	}

	/**
	 * Insert Doctor Data
	 */
	public void insertDoctorData() {
		try {
			doctorDao.insert("did", "dname", "speciality", "contactno", "fee", "degree");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
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
				logger.debug("\nDoctor not found!");
			} else {

				try {
					Map<String, String> aDoctor = doctorDao.searchById(id);
					if (aDoctor.size() != 0) {
						System.out.println(
								"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
						System.out.printf("%10s  %15s  %15s  %15s  %10s  %20s\n", aDoctor.get(DoctorDao.ID),
								aDoctor.get(DoctorDao.NAME), aDoctor.get(DoctorDao.SPECIALITY),
								aDoctor.get(DoctorDao.CONTACT_NO), aDoctor.get(DoctorDao.FEE),
								aDoctor.get(DoctorDao.DEGREE));
						System.out.println(
								"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
					} else {
						logger.debug("Record is not found");
					}
					logger.debug("\nRecord Find Successfully...");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			logger.debug(e.getMessage());
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
				logger.debug("\nDoctor not found!");
			} else {
				try {
					doctorDao.delete(did);
					logger.debug("\nRecord Deleted Successfully...");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show All List Of Doctor
	 */
	public void listAllDoctor() {
	
		try {
			List<Map<String, String>> doctorList = doctorDao.list();
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			for (Map<String, String> aDoctor : doctorList) {
				System.out.printf("%10s  %15s  %15s  %15s  %10s  %20s\n", aDoctor.get(DoctorDao.ID),
						aDoctor.get(DoctorDao.NAME), aDoctor.get(DoctorDao.SPECIALITY),
						aDoctor.get(DoctorDao.CONTACT_NO), aDoctor.get(DoctorDao.FEE), aDoctor.get(DoctorDao.DEGREE));
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
}
