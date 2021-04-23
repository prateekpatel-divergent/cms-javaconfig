package com.divergentsl.cmsjavaconfig;

import java.sql.SQLException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.AdminDao;



/**
 * Admin Class For Login and Update into all infromation
 * 
 * @author Divergent
 *
 */
@Component
public class Admin {

	private static Logger logger = LoggerFactory.getLogger(Admin.class);
	
	@Autowired
	Environment env;
	
	@Autowired
	private  AdminDao adminDao;

	@Autowired
	private MainMenu mainMenu;
	
	@Autowired
	private Patient patient;
	
	@Autowired
	private CurdDoctor curdDoctor;
	
	@Autowired
	private Drug drug;
	
	@Autowired
	private LabTest labTest;
	
	@Autowired
	private Appoinment appoinment;
	
	/**
	 * Admin Login
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 */
	public boolean adminLogin() throws SQLException {
		try {

			Scanner sc = new Scanner(System.in);
			System.out.println("---------------Admin Login -------------------");
			System.out.println("Enter Username : ");
			String username = sc.nextLine();
			System.out.println("Enter Password : ");
			String password = sc.nextLine();
			if (adminDao.adminLogin(username, password)) {
				logger.info("Admin Login Successful");
				return true;
			} else {
				logger.info("Incorrect Username & Password");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void printAdminOptions() {
		while (true) {
			executedMethod();
		}
	}

	public void executedMethod() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n----Admin Panel-----");
		System.out.println(
				"1.  Patient \n  2.  Doctor \n  3. 	Drug \n  4.	Lab Test \n  5. 	Book appointment for a patient by selecting Patient, Doctor and Data/time \n  6. 	Logout");
		System.out.println("Enter Your Choice: ");

		int input = sc.nextInt();

		switch (input) {
		case 1:
			patient.patientPanel();
			break;
		case 2:
			curdDoctor.docterPanel();
			break;
		case 3:
			drug.drugPanel();
			break;
		case 4:
			labTest.labTestPanel();
			break;
		case 5:
			appoinment.appoinmentPanel();
			break;
		case 6:
			logger.info("Logout Successfully");
			try {
				mainMenu.startAgain();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			logger.info("Back");
			break;
		}
	}

}
