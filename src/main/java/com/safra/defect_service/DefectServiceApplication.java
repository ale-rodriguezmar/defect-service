package com.safra.defect_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

public class DefectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefectServiceApplication.class, args);
	}

}
