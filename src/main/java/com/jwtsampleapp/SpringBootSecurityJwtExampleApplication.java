package com.jwtsampleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootSecurityJwtExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtExampleApplication.class, args);
	}

}
