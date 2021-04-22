package com.divergentsl.cmsjavaconfig;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.AppoinmentDao;

/**
 * Appointment All Patient
 * 
 * @author Divergent
 *
 */
@Component
public class Appoinment {
	

	private static Logger logger = LoggerFactory.getLogger(Appoinment.class);
	
	@Autowired
	public AppoinmentDao appoinmentDao;

	/**
	 * Get Patient Information
	 * 
	 * @return
	 */
	public Map<String, String> inputAppoinmentData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Appoinment Id ");
		String appoinid = sc.nextLine();
		System.out.println("Enter Patient Name");
		String patientname = sc.nextLine();
		System.out.println("Enter Docter Name");
		String doctername = sc.nextLine();
		System.out.println("Enter Problem");
		String problem = sc.nextLine();
		System.out.println("Enter Appoinment Date");
		String appoindate = sc.nextLine();
		System.out.println("Enter doctor id");
		String doctorid = sc.nextLine();
		System.out.println("Enter Patient Id");
		String patientid = sc.nextLine();

		Map<String, String> map = new HashMap<>();
		map.put("1", appoinid);
		map.put("2", patientname);
		map.put("3", doctername);
		map.put("4", problem);
		map.put("5", appoindate);
		map.put("6", doctorid);
		map.put("7", patientid);
		return map;
	}

	/**
	 * All Information Stored By This Method
	 */
	public void appoinmentPanel() {
		try {
			appoinmentDao.insert("appoinid", "patientname", "doctername", "problem", "appoindate","currentdate", "doctorid", "patientid");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}

	/**
	 * Show All Appointment
	 */
	public void showAllAppoinment() {
		try {
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Docter Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			List<Map<String, String>> patientList = appoinmentDao.list();

			for (Map<String, String> patient : patientList) {
				System.out.printf("%5s  %15s  %20s  %15s  %15s  %20s  %5s  %5s\n", patient.get(AppoinmentDao.ID),
						patient.get(AppoinmentDao.PNAME), patient.get(AppoinmentDao.DNAME),
						patient.get(AppoinmentDao.APPOINMENTDATE), patient.get(AppoinmentDao.CURRENTDATE),
						patient.get(AppoinmentDao.PROBLEM), patient.get(AppoinmentDao.PID),
						patient.get(AppoinmentDao.PID));
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");

		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
}
