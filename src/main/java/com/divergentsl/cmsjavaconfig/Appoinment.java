package com.divergentsl.cmsjavaconfig;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.divergentsl.cmsjavaconfig.dao.AppoinmentDao;
import com.divergentsl.cmsjavaconfig.dto.AppoinmentDto;
import com.divergentsl.cmsjavaconfig.dto.DoctorDto;

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
	private AppoinmentDao appoinmentDao;

	/**
	 * Get Patient Information
	 * 
	 * @return
	 */
	public Map<String, String> inputAppoinmentData() {
		Scanner sc = new Scanner(System.in);
		AppoinmentDto appoinmentDto = new AppoinmentDto();
		System.out.println("Enter Appoinment Id ");
		String appoinid = sc.nextLine();
		appoinmentDto.setID(appoinid);
		System.out.println("Enter Patient Name");
		String patientname = sc.nextLine();
		appoinmentDto.setPNAME(patientname);
		System.out.println("Enter Docter Name");
		String doctername = sc.nextLine();
		appoinmentDto.setDNAME(doctername);
		System.out.println("Enter Problem");
		String problem = sc.nextLine();
		appoinmentDto.setPROBLEM(problem);
		System.out.println("Enter Appoinment Date");
		String appoindate = sc.nextLine();
		appoinmentDto.setAPPOINMENTDATE(appoindate);
		System.out.println("Enter doctor id");
		String doctorid = sc.nextLine();
		appoinmentDto.setDID(doctorid);
		System.out.println("Enter Patient Id");
		String patientid = sc.nextLine();
		appoinmentDto.setPID(patientid);

		Map<String, String> map = new HashMap<>();
		map.put("1", appoinid);
		map.put("2", patientname);
		map.put("3", doctername);
		map.put("4", problem);
		map.put("5", appoindate);
		map.put("6", doctorid);
		map.put("7", patientid);

		if (validateAppoinment(appoinmentDto)) {
			return null;
		}
		return map;
	}

	/**
	 * All Information Stored By This Method
	 */
	public void appoinmentPanel() {
		try {
			Map<String, String> map = inputAppoinmentData();
			if (map != null) {
				appoinmentDao.insert(map.get("1"), map.get("2"), map.get("3"), map.get("4"), map.get("5"),
						map.get("curdate"), map.get("6"), map.get("7"));
				logger.info("Insert Successfully");
			} else {
				logger.info("invalid Input");
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * validatorFactory method for validation
	 * 
	 * @param appoinmentDto
	 * @return
	 */
	private boolean validateAppoinment(AppoinmentDto appoinmentDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<AppoinmentDto>> violations = validator.validate(appoinmentDto);
		for (ConstraintViolation<AppoinmentDto> violation : violations) {
			logger.error(violation.getMessage());
		}
		return violations.size() > 0;
	}

	/**
	 * Show All Appointment
	 */
	public void showAllAppoinment() {
		try {
			System.out.println(
					"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Appoinment Data*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			List<Map<String, Object>> patientList = appoinmentDao.list();

			for (Map<String, Object> patient : patientList) {
				System.out.printf("%5s  %15s  %20s  %15s  %15s  %20s  %5s  %5s\n", patient.get(AppoinmentDao.ID),
						patient.get(AppoinmentDao.PNAME), patient.get(AppoinmentDao.DNAME),
						patient.get(AppoinmentDao.APPOINMENTDATE), patient.get(AppoinmentDao.CURRENTDATE),
						patient.get(AppoinmentDao.PROBLEM), patient.get(AppoinmentDao.PID),
						patient.get(AppoinmentDao.PID));
			}
			System.out.println(
					"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");

		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}
}
