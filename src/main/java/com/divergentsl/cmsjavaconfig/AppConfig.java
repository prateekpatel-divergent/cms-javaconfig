package com.divergentsl.cmsjavaconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.divergentsl.cmsjavaconfig","com.divergentsl.cmsjavaconfig.dao"})
@Profile("dev")
@PropertySource("classpath:/app.properties")
public class AppConfig {

}
