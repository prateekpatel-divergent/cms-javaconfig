package com.divergentsl.cmsjavaconfig;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("mainMenu")
public class MainMenu {
	
	private static Logger logger = LoggerFactory.getLogger(MainMenu.class);
	
	@Autowired
	private Doctor doctor;
	
	@Autowired
	private Admin admin;
	
	@Autowired
	Environment env;
	/**
	 * Admin Start Method
	 * 
	 * @throws SQLException
	 */
	public String startAgain() throws SQLException {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\n----Login Panel----");
			System.out.println("1. Admin");
			System.out.println("2. Doctor");
			System.out.println("3. Exit");
			String input = sc.nextLine();
			switch (input) {
			case "1":
				if (admin.adminLogin()) {
					while (true) {
						admin.printAdminOptions();
						if (sc.nextLine().equals("6")) {
							startAgain();
							break;
						} else {
							logger.debug("Select Right Option");
						}
					}
				} else {
					logger.debug("You are not Authorised");
				}
				break;

			case "2":
				if (doctor.doctorLogin()) {
					while (true) {
						doctor.printDoctorOptions();
						if (sc.nextLine().equals("5")) {
							startAgain();
							break;
						} else {
							logger.debug("Select Right Option");
						}
					}

				} else {
					logger.debug("You are not Authorised");
				}
				break;

			case "3":
				logger.info("Exit");
				System.exit(0);
				break;

			default:
				logger.info("Invalid Input");
				break;
			}
		}
	}
	
	public String getPropertyValue() {
		return env.getProperty("test");
	}
}
