package com.gio.soap.webservices.soapcoursemanagment.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode=FaultCode.CUSTOM, 
customFaultCode="{http://gio.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6175742254728450703L;

	public CourseNotFoundException(String message) {
		super(message);
	}
	
	

}
