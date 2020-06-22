package com.poc.code.introwebservice.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All User Attributes")
public class User {
	
	private int id;
	
	@Min(value = 2,message="Minimum length of name should be 2 characters")
	@ApiModelProperty(notes  = "Minimum length of name should be 2 characters")
	private String name;
	
	@Min(value = 15, message = "Age should not be less than 15")
    @Max(value = 65, message = "Age should not be greater than 65")
	private int age;
	

	public User() {
	}
	
	public User(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
