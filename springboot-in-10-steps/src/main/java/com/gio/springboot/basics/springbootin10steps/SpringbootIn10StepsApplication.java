package com.gio.springboot.basics.springbootin10steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/*
 * 1) indicates Spring Context 
 * 2) enables AutoConfiguration
 *    which looks at Frameworks available on CLASSPATH
 *    and existing configuration for the application
 *    Based on this, Spring Boot provides basic configuration
 *    needed to configure the application with theses frameworks
 * 3) enables ComponentScan
 */

@SpringBootApplication
public class SpringbootIn10StepsApplication {

	public static void main(String[] args) {
		
		// used to run a Spring Context, this AutoConfigures the app
		ApplicationContext appContext = SpringApplication.run(SpringbootIn10StepsApplication.class, args);
		
		
		// displays all the auto config beans created
		for(String name: appContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}
}
