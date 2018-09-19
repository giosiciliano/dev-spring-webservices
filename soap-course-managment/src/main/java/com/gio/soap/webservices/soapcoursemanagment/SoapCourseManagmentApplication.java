package com.gio.soap.webservices.soapcoursemanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoapCourseManagmentApplication {

	/*
	 * Spring WS SOAP steps
	 * 1) What Request/Response XML should look like
	 * 2) Create XSD - XML schema definition
	 * 3) Create Endpoint - accepts request returns response
	 * 4) Create WebServiceConfig - maps servlet to url, 
	 *    creates WSDL - web service description language 
	 * 5)
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(SoapCourseManagmentApplication.class, args);
	}
}
