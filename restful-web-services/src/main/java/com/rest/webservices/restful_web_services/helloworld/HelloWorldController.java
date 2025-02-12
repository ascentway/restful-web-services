package com.rest.webservices.restful_web_services.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping(path = "/hello-World")
	public String helloWorld()
	{
		return "Hello World	";
	}

	@GetMapping(path = "/hello-World-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello World");
	}
	
	//Path parameters
	// /users/{id}/todos/{id} => /users/2/todos/200
	// /hello-world/path-variable/{name}
	// /hello-world/path-variable/Dhruv
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s",name));
	}
	
	@GetMapping(path = "/hello-World-internationalized")
	public String helloWorldInternationlized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
	//return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
}
//	@GetMapping(path = "/hello-World-internationalized")
//	public String helloWorldInternationalized()
//	{
//		return "Hello World	V2";
//	}

