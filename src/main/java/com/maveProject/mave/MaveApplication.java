package com.maveProject.mave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaveApplication.class, args);
	}

}
