package com.gio.soap.webservices.soapcoursemanagment.soap;

import java.util.Collections;
import java.util.List;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/*
 * Spring configuration
 * Enable Spring Web Services
 */
@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter{

	/*
	 * MessageDispatcherServlet (front controller - first processed here, determines
	 * what controller to execute next) Passes - ApplicationContext - url -> /ws/*
	 */

	// helps map servlet to url
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {

		// Servlet to handle requests
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		// mapped to url
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");

	}
	
	/*
	 * Create WSDL /ws/courses.wsdl
	 * define: 
	 * 	- PortType: CoursePort
     *  - NameSpace: http://gio.com/courses
	 * use schema course-details.xsd to generate wsdl
	 */
	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		
		// need to set PortType, Namespace, /ws, and schema
		definition.setPortTypeName("CoursePort"); // interface that defines types
		definition.setTargetNamespace("http://gio.com/courses");
		definition.setLocationUri("/ws");
		definition.setSchema(coursesSchema);
		
		return definition;
	}
	
	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
	
	// configure XwsSecurityInterceptor - intercepts all requests coming in
	// and checks to see if its secure
	@Bean
	public XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		securityInterceptor.setCallbackHandler(callbackHandler());
		
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		return securityInterceptor;
	}
	
	@Bean // needs to be public
	public SimplePasswordValidationCallbackHandler callbackHandler() {
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		handler.setUsersMap(Collections.singletonMap("user", "password"));
		return handler;
	}

	//		Callback Handler -> SimplePasswordValidationCallbackHandler
	//		Security Policy  -> securityPolicy.xml
	// add with Intercepterors.add
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}
	
}
