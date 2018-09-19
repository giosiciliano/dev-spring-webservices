package com.gio.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * GOOD VERSIONING ARTICLES
 * - https://www.mnot.net/blog/2011/10/25/web_api_versioning_smackdown
 * - http://urthen.github.io/2013/05/09/ways-to-version-your-api/
 */

@RestController
public class PersonVersioningController {
	
	/*
	 * VERSIONING MAPPING DIFFERENT URIs
	 * 
	 * using a different URI path v1/person or v2/person
	 * 
	 */
	
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	/* 
	 * VERSIONING USING REQUEST PARAMS
	 * 
	 * using a param: person/param?version=1
	 * 
	 */
	
	@GetMapping(value="person/param", params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="person/param", params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	/*
	 * VERSIONING USING HEADER PARAMS
	 * 
	 * using HEADER key = X-API-VERSION value = 1 or 2
	 * 
	 * cant be executed in browser w/o plugin
	 */
	
	@GetMapping(value="person/header", headers="X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="person/header", headers="X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	/*
	 * VERSIONING USING PRODUCES (ACCEPT HEADER)
	 * 
	 * using HEADER key = ACCEPT; value=application/vnd.company.app-v1+json
	 * 
	 * can't be executing in browser w/o plugin
	 */
	
	@GetMapping(value="person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="person/produces", produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
