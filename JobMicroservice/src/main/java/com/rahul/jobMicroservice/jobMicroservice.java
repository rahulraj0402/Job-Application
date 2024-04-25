package com.rahul.jobMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class jobMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(jobMicroservice.class, args);
	}

}
