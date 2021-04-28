package com.divergentsl.cmsjavaconfig.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppoinmentAspect {
	private static Logger logger = LoggerFactory.getLogger(AppoinmentAspect.class);

	@Before("execution(* com.divergentsl.cmsjavaconfig.dao.AppoinmentDao.*(..))")
	public void beforeMethod() {
		logger.info("Before Method Called!!!");
	}

	@AfterReturning("execution(* com.divergentsl.cmsjavaconfig.dao.AppoinmentDao.*(..))")
	public void afterReturning() {
		logger.info("After returning Called!!!");
	}
}
