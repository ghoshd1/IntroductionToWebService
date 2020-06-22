package com.poc.code.introwebservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.poc.code.introwebservice.dto.DummyBean;

/*
 * Dynamic Filtering 
 */

@RestController
public class DummyController {
	
	@GetMapping("/getBean")
	public MappingJacksonValue getDummyBean() {
		DummyBean dummyBean = new DummyBean("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		
		SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().addFilter("dummyBean",filter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dummyBean);
		mappingJacksonValue.setFilters(simpleFilterProvider);
		
		return mappingJacksonValue;
	}

	@GetMapping("/getBeanList")
	public MappingJacksonValue getDummyBeanList() {
		DummyBean dummyBean = new DummyBean("value1", "value2", "value3");
		DummyBean dummyBean1 = new DummyBean("value11", "value21", "value31");
		
		List<DummyBean> dummyList = Arrays.asList(dummyBean,dummyBean1);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().addFilter("dummyBean",filter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dummyList);
		mappingJacksonValue.setFilters(simpleFilterProvider);
		
		return mappingJacksonValue;
		
	}
}
