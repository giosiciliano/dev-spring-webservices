package com.gio.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	// map field1, field2 (dynamic filtering)
	@GetMapping("/filtering")
	public MappingJacksonValue retreiveSomeBean() {
		
		SomeBean sb = new SomeBean("val1", "val2", "val3");

		// --- Example of Dynamic filtering
		MappingJacksonValue mapping = new MappingJacksonValue(sb);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mapping.setFilters(filters);
		// ---
		
		return mapping;
	}

	// map field2, field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retreiveListOfSomeBeans() {
		
		List<SomeBean> list = Arrays.asList(new SomeBean("val1", "val2", "val3"),
				new SomeBean("val11", "val21", "val31"));
		
		// --- Example of Dynamic filtering
		MappingJacksonValue mapping = new MappingJacksonValue(list);
				
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
				
		mapping.setFilters(filters);
		// ---
		
		return mapping;
	}
}
