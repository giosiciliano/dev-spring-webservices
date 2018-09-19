package com.gio.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

// tell spring this is a controller
@RestController
public class HelloWorldController {
	
	// used for i18n
	@Autowired
	private MessageSource messageSource;
	
	// GET
	// URI - /hello-world
	// method - return "Hello World"
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World!";
	}

	// GET hello-world-bean
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	// GET hello-world-bean w/ path variable
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	// GET hello-world-internationalized w/ internationalization (i18n)
	// customized based on where the request is coming from
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorlInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
		
	/*	OLD WAY
	 * 
	 * 	public String helloWorlInternationalized(@RequestHeader(name="Accept-language", required=false) Locale locale) {
	 *		return messageSource.getMessage("good.morning.message", null, locale);
	 *  }
	 */
	
}
