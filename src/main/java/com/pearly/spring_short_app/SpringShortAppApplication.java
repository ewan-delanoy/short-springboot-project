package com.pearly.spring_short_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringShortAppApplication {

	public static void main(String[] args) {

		System.out.println("Before");
		SpringApplication.run(SpringShortAppApplication.class, args);
	}

}
