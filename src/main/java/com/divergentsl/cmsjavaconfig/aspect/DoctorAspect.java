package com.divergentsl.cmsjavaconfig.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DoctorAspect {
	private static Logger logger = LoggerFactory.getLogger(DoctorAspect.class);

	@Before(value = "execution(* com.divergentsl.cmsjavaconfig.dao.DoctorDao.*(..))")
	public void beforeMethod() {
		logger.info("Before method Called!!!");
	}
	@AfterReturning(value = "execution(* com.divergentsl.cmsjavaconfig.dao.DoctorDao.*(..))")
	public void afterReturning() {
		logger.info("After returning Called!!!");
	}

}
