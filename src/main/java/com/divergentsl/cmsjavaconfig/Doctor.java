package com.divergentsl.cmsjavaconfig;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

	private static Logger logger = LoggerFactory.getLogger(Doctor.class);
	
	@Autowired
	private MainMenu mainMenu;
	
	@Autowired
	private DoctorLoginDao doctorLoginDao;
	
	@Autowired
	private Patient patient;
	
	@Autowired
	private PrescriptionAndNotes prescriptionAndNotes;
	
	@Autowired
	private Appoinment appoinment;
	
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
				PrescriptionAndNotes.historyAndPresciption();
				break;
			case 5:
				patient.generateInvoice();
				break;
			case 6:
				logger.info("Logout Successfully");
				try {
					mainMenu.startAgain();
				} catch (SQLException e) {
					logger.info(e.getMessage());
					e.printStackTrace();
				}
				break;
			default:
				logger.info("Choice Right Option");
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
			logger.info("Doctor Login Successful");
				return true;
			} else {
				logger.info("Incorrect Username & Password");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return false;
	}

}
