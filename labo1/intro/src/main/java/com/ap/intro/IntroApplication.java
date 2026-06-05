package com.ap.intro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ap.intro.service.GradeService;

@SpringBootApplication
public class IntroApplication implements CommandLineRunner {

	private final GradeService service;

	public IntroApplication(GradeService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(IntroApplication.class, args);
	}

	@Override
	public void run(String... args) {
		service.addGrade("John", "Doe", 85);
	}

}
