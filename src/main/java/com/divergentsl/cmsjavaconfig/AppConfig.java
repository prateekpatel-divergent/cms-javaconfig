package com.divergentsl.cmsjavaconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.divergentsl.cmsjavaconfig","com.divergentsl.cmsjavaconfig.dao"})
public class AppConfig {

}
