package com.ap.movies;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ap.movies.service.MoviesService;

@SpringBootApplication
public class MoviesApplication implements CommandLineRunner {

	private final MoviesService moviesService;

	public MoviesApplication(MoviesService moviesService) {
		this.moviesService = moviesService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	 @Override
    public void run(String... args) {
        // Seed data
        moviesService.addMovie("The Big Lebowski", "1998", "Jeff Bridges, John Goodman, John Turturro, Steve Buscemi");
        moviesService.addMovie("Pulp Fiction", "1994", "John Travolta, Uma Thurman, Samuel L. Jackson");

        System.out.println("Database seeded. Total movies: " + moviesService.getMoviesCount());
    }

}
