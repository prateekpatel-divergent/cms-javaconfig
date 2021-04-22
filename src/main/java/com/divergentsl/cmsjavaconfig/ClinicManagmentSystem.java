package com.divergentsl.cmsjavaconfig;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main Class In Which We can Do a admin and Doctor Login
 * 
 * @author Divergent
 *
 */

public class ClinicManagmentSystem {

	private static Logger logger = LoggerFactory.getLogger(ClinicManagmentSystem.class);

	/**
	 * Main Method
	 * 
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		logger.debug("main method");
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		logger.debug("ApplicationContext:{}", context);
		MainMenu mainMenu = context.getBean("mainMenu", MainMenu.class);
		logger.debug("MainMenu:{}", mainMenu);
		mainMenu.startAgain();
	}

}
