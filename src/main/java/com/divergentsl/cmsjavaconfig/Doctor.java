package com.divergentsl.cmsjavaconfig;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.DoctorLoginDao;
import com.divergentsl.cmsjavaconfig.MainMenu;


/**
 * Doctor Class In Which All CRUD Operaton Done
 * 
 * @author Divergent
 *
 */
@Component
public class Doctor {

	private static Logger logger = LoggerFactory.getLogger(ClinicManagmentSystem.class);
	
	@Autowired
	public MainMenu mainMenu;
	
	@Autowired
	public DoctorLoginDao doctorLoginDao;
	
	@Autowired
	public Patient patient;
	
	@Autowired
	public PrescriptionAndNotes prescriptionAndNotes;
	
	@Autowired
	public Appoinment appoinment;
	
	/**
	 * Get Doctor Data
	 */
	public void printDoctorOptions() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\nDoctor Panel");
			System.out.println("1. List of patient");
			System.out.println("2. Add prescription and notes for a patient");
			System.out.println("3. See booked appointments for him");
			System.out.println("4. Check patient history and his prescription");
			System.out.println("5. Generate Invoice");
			System.out.println("6. Logout");
			System.out.println("Enter Your Choice: ");

			int input = sc.nextInt();

			switch (input) {
			case 1:
				patient.listAllPatientData();
				break;
			case 2:
				prescriptionAndNotes.prescriptionPatient();
				break;
			case 3:
				appoinment.showAllAppoinment();
				break;
			case 4:
				prescriptionAndNotes.historyAndPresciption();
				break;
			case 5:
				patient.generateInvoice();
				break;
			case 6:
				logger.debug("Logout Successfully");
				try {
					mainMenu.startAgain();
				} catch (SQLException e) {
					logger.debug(e.getMessage());
					e.printStackTrace();
				}
				break;
			default:
				logger.debug("Choice Right Option");
				break;
			}
		}
	}

	/**
	 * Doctor Login Method
	 * 
	 * @return
	 */
	public boolean doctorLogin() {

		Scanner sc = new Scanner(System.in);
		try {

			System.out.println("\n-----Doctor Login------");
			System.out.println("\nEnter Username: ");
			String username = sc.nextLine();

			System.out.println("\nEnter Password: ");
			String password = sc.nextLine();
			if (doctorLoginDao.doctorLogin(username, password)) {
			logger.debug("Doctor Login Successful");
				return true;
			} else {
				logger.debug("Incorrect Username & Password");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		return false;
	}

}
