package com.poc.code.introwebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@Autowired
	MessageSource messageSource;
	
	@GetMapping("/welcome")
	public String welcomeMessage() {
		return messageSource.getMessage("welcome.message", null, LocaleContextHolder.getLocale());
	}
}
