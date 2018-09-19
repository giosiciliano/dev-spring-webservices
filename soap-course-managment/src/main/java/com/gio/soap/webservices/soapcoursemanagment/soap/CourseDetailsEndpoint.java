package com.gio.soap.webservices.soapcoursemanagment.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.gio.courses.CourseDetails;
import com.gio.courses.DeleteCourseDetailsRequest;
import com.gio.courses.DeleteCourseDetailsResponse;
import com.gio.courses.GetAllCourseDetailsRequest;
import com.gio.courses.GetAllCourseDetailsResponse;
import com.gio.courses.GetCourseDetailsRequest;
import com.gio.courses.GetCourseDetailsResponse;
import com.gio.soap.webservices.soapcoursemanagment.soap.bean.Course;
import com.gio.soap.webservices.soapcoursemanagment.soap.exception.CourseNotFoundException;
import com.gio.soap.webservices.soapcoursemanagment.soap.service.CourseDetailsService;
import com.gio.soap.webservices.soapcoursemanagment.soap.service.CourseDetailsService.Status;

// Tells Spring that this is a SOAP endpoint (will accept a request and send response)
@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;
	
	//method to accept a request to return response
	//input - GetCourseDetailsRequest
	//output - GetCourseDetailsResponse
	
	// @PayLaodRoot - supports namespace/name http://gio.com/courses / GetCourseDetailsRequest
	// @RequestPayload - convert xml (input) into a java object
	// @ResponsePayload - convert java (output) into a xml
	@PayloadRoot(namespace="http://gio.com/courses", 
			localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse procesCourseDetailsRequest
	(@RequestPayload GetCourseDetailsRequest req) {
		
		Course course = service.findById(req.getId());
		
		if(course==null) {
			throw new CourseNotFoundException("Invalid Course ID " + req.getId());
		}
		
		return mapCourseDetails(course);
	}
	
	@PayloadRoot(namespace="http://gio.com/courses", 
			localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse procesAllCourseDetailsRequest
	(@RequestPayload GetAllCourseDetailsRequest req) {
		
		List<Course> courses = service.findAll();
		return mapAllCourseDetails(courses);
	}
	
	@PayloadRoot(namespace="http://gio.com/courses", 
			localPart="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest
	(@RequestPayload DeleteCourseDetailsRequest req) {
		
		Status status = service.deleteById(req.getId());
		
		DeleteCourseDetailsResponse resp = new DeleteCourseDetailsResponse();
		resp.setStatus(mapStatus(status));
		
		return resp;
	}

	private com.gio.courses.Status mapStatus(Status status) {
		if (status==Status.FAILURE) {
			return com.gio.courses.Status.FAILURE;
		}
		return com.gio.courses.Status.SUCCESS;
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse resp = new GetCourseDetailsResponse();
		
		resp.setCourseDetails(mapCourse(course));
		
		return resp;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse resp = new GetAllCourseDetailsResponse();
		
		for(Course course:courses) {
			CourseDetails mapCourse = mapCourse(course);
			resp.getCourseDetails().add(mapCourse);
		}
		
		return resp;
	}
	
	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

}
