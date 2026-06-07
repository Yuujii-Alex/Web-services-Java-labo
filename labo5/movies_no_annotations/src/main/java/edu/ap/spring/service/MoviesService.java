package edu.ap.spring.service;

import org.springframework.stereotype.Service;

import edu.ap.spring.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MoviesService {

    private final RedisService service;

    public MoviesService(RedisService service) {
        this.service = service;
    }

    public List<Movie> getMoviesList() {
        List<Movie> movieList = new ArrayList<>();
        Set<String> movies = service.keys("movies:*");
        for (String m : movies) {
            // get all parts of the key, eg : ["movies", "1998", "The Big Lebowski"]
            String[] parts = m.split(":");
            String title = parts[2];
            String year = parts[1];

            // get the actors
            List<String> actors = service.getList(m);

            // create the result string
            Movie movie = new Movie(title, year, actors);
            movieList.add(movie);
        }
        return movieList;
    }

    public long getMoviesCount() {
        String count = service.getKey("moviescount");
        return count == null ? 0 : Long.parseLong(count);
    }

    public void addMovie(String name, String year, String actors) {
        String key = "movies:" + year + ":" + name;
        String[] parts = actors.split(",");
        for (String actor : parts) {
            this.service.rpush(key, actor);
        }
        this.service.incr("moviescount");
    }
}
