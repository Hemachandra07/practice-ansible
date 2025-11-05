package com.klef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EmployeeSpringbootApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSpringbootApplication.class, args);
		System.out.println("Hii Devops");
	}

}
