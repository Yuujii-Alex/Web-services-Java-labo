package edu.ap.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.ap.spring.service.RedisService;

@SpringBootApplication
public class RedisApplication implements CommandLineRunner {
	
	private final RedisService service;

	public RedisApplication(RedisService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// empty db
		this.service.flushDb();

		// movies
		this.service.setKey("moviescount", "0");
		this.service.rpush("movies:1998:The Big Lebowski", "Jeff Bridges");
		this.service.rpush("movies:1998:The Big Lebowski", "John Goodman");
		this.service.rpush("movies:1998:The Big Lebowski", "John Turturro");
		this.service.rpush("movies:1998:The Big Lebowski", "Steve Buscemi");
		this.service.incr("moviescount");

		System.out.println("Total movies : " + service.getKey("moviescount"));
	}
}
