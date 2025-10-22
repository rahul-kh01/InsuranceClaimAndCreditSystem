package com.claim.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessingApplication.class, args);
	}

}
