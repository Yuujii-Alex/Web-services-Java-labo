package com.ap.rest;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ap.rest.controller.InfractionDTO;
import com.ap.rest.service.InfractionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

@SpringBootApplication
public class RestApplication implements CommandLineRunner {

	@Value("classpath:static/infractions.json")
	public InputStream data;

	private final InfractionService service;

	public RestApplication(InfractionService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Override
	public void run(String... args) {
		InfractionDTO[] infractions = null;
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.registerModule(new JaxbAnnotationModule());

		try {
			infractions = objectMapper.readValue(data, InfractionDTO[].class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (InfractionDTO infraction : infractions) {
			service.save(infraction);
		}
	}

}
