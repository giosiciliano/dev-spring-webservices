package com.gio.soap.webservices.soapcoursemanagment.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gio.soap.webservices.soapcoursemanagment.soap.bean.Course;

@Component
public class CourseDetailsService {
	
	public enum Status {
		SUCCESS, FAILURE;
	}
	
	private static List<Course> courses = new ArrayList<>();
	
	static {
		courses.add(new Course(1, "Spring", "10 Steps"));
		courses.add(new Course(2, "Spring MVC", "10 Examples"));
		courses.add(new Course(3, "Spring Boot", "6k Students"));
		courses.add(new Course(4, "Maven", "Popular Maven course"));
	}
	
	// get course details (find by id)
	public Course findById(int id) {
		for(Course course:courses) {
			if(course.getId() == id) {
				return course;
			}
		}
		return null;
	}
	
	// get all courses details
	public List<Course> findAll() {
		return courses;
	}
	
	// delete courses
	public Status deleteById(int id) {
		
		Iterator<Course> iterator = courses.iterator();
		while(iterator.hasNext()) {
			Course course = iterator.next();
			if(course.getId() == id) {
				iterator.remove();
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}
	
	// updating course and new course

}
