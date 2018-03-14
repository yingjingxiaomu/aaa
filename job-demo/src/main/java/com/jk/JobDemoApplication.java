package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobDemoApplication.class, args);
	}
}
