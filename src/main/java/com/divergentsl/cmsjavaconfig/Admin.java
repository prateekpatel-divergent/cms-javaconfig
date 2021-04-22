package com.divergentsl.cmsjavaconfig;

import java.io.Console;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
	public  AdminDao adminDao;

	@Autowired
	public MainMenu mainMenu;
	
	@Autowired
	public Patient patient;
	
	@Autowired
	public CurdDoctor curdDoctor;
	
	@Autowired
	public Drug drug;
	
	@Autowired
	public LabTest labTest;
	
	@Autowired
	public Appoinment appoinment;
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
			Console cons = System.console();
			System.out.println("---------------Admin Login -------------------");
			System.out.println("Enter Username : ");
			String username = sc.nextLine();
			System.out.println("Enter Password : ");
			String password = sc.nextLine();
			if (adminDao.adminLogin(username, password)) {
				logger.debug("Admin Login Successful");
				return true;
			} else {
				logger.debug("Incorrect Username & Password");
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
			logger.debug("Logout Successfully");
			try {
				mainMenu.startAgain();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			logger.debug("Back");
			break;
		}
	}

}
