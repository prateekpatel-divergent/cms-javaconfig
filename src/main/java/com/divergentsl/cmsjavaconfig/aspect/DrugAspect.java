package com.divergentsl.cmsjavaconfig.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DrugAspect {
	private static Logger logger = LoggerFactory.getLogger(DrugAspect.class);

	@Before("execution(* com.divergentsl.cmsjavaconfig.dao.DrugDao.*(..))")
	public void beforeMethod() {
		logger.info("Before Method Called!!!");
	}

	@After("execution(* com.divergentsl.cmsjavaconfig.dao.DrugDao.*(..))")
	public void afterReturning() {
		logger.info("After returning Called!!!");
	}
}
