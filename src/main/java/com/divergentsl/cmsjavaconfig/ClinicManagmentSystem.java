package com.divergentsl.cmsjavaconfig;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
		logger.info("main method");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("dev");
		context.register(AppConfig.class);
		context.refresh();
		logger.debug("ApplicationContext:{}", context);
		MainMenu mainMenu = context.getBean("mainMenu", MainMenu.class);
		logger.info("Property Value:{}",mainMenu.getPropertyValue());
		logger.info("MainMenu:{}", mainMenu.startAgain());
	}

}
