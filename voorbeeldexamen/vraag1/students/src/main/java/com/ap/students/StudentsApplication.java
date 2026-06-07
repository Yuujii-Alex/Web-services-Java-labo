package com.ap.students;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ap.students.service.StudentService;

@SpringBootApplication
public class StudentsApplication implements CommandLineRunner {

	private final StudentService service;

	public StudentsApplication(StudentService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.createStudent("alex", "guo", LocalDate.of(2002, 03, 15), "Web Services Java");
	}

}
