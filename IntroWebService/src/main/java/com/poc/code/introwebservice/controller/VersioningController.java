package com.poc.code.introwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.code.introwebservice.dto.PersonV1;
import com.poc.code.introwebservice.dto.PersonV2;

@RestController
public class VersioningController {

//Approach 1: Different URI
	
	@GetMapping("/person/v1")
	public PersonV1 getPersonV1() {
		return new PersonV1("Debpratim Ghosh");
	}
	
	@GetMapping("/person/v2")
	public PersonV2 getPersonV2() {
		return new PersonV2("Debpratim","Ghosh");
	}

//Approach 2: Request Param
	
	@GetMapping(value = "/person/param",params="version=1")
	public PersonV1 getPersonParamV1() {
		return new PersonV1("Debpratim Ghosh");
	}
	
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 getPersonParamV2() {
		return new PersonV2("Debpratim","Ghosh");
	}
	
//Header Param	
	@GetMapping(value = "/person/header",headers ="X-API-VERSION=1")
	public PersonV1 getPersonHeaderV1() {
		return new PersonV1("Debpratim Ghosh");
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=2")
	public PersonV2 getPersonHeaderV2() {
		return new PersonV2("Debpratim","Ghosh");
	}

	// Content Negotiation
	@GetMapping(value = "/person/header", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonProducesV1() {
		return new PersonV1("Debpratim Ghosh");
	}

	@GetMapping(value = "/person/header", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonProducesV2() {
		return new PersonV2("Debpratim", "Ghosh");
	}
}
