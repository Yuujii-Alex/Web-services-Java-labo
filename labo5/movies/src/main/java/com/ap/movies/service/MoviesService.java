package com.ap.movies.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ap.movies.model.Movie;

/**
 * MoviesService zonder RedisTemplate.
 * Alle Redis interactie verloopt via Spring Cache annotaties:
 *
 *  @Cacheable  — geeft gecachede waarde terug, of voert methode uit bij cache miss
 *  @CachePut   — voert methode altijd uit en werkt cache bij
 *  @CacheEvict — verwijdert entries uit de cache
 */
@Service
public class MoviesService {

    // In-memory opslag die de "database" simuleert.
    // In een echte app zou dit een JPA repository zijn.
    private final List<Movie> database = new ArrayList<>();

    /**
     * Haalt alle films op.
     * Resultaat wordt gecached onder de sleutel "movies::all".
     * Bij een tweede aanroep komt het resultaat uit Redis — de methode zelf wordt niet uitgevoerd.
     */
    @Cacheable(value = "movies", key = "'all'")
    public List<Movie> getMoviesList() {
        System.out.println(">>> Cache miss — ophalen uit database");
        return new ArrayList<>(database);
    }

    /**
     * Haalt het aantal films op.
     * Gecached onder "moviescount::total".
     */
    @Cacheable(value = "moviescount", key = "'total'")
    public long getMoviesCount() {
        System.out.println(">>> Cache miss — tellen in database");
        return database.size();
    }

    /**
     * Voegt een film toe aan de database.
     *
     * @CachePut werkt de cache bij met het nieuwe resultaat (volledige lijst).
     * @CacheEvict wist de teller zodat die opnieuw berekend wordt.
     */
    @CachePut(value = "movies", key = "'all'")
    @CacheEvict(value = "moviescount", key = "'total'")
    public List<Movie> addMovie(String title, String year, String actorsInput) {
        List<String> actors = List.of(actorsInput.split(","))
                .stream()
                .map(String::trim)
                .toList();

        database.add(new Movie(title, year, actors));
        return new ArrayList<>(database);
    }
}
