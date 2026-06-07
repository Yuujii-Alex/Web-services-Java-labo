package edu.ap.spring.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ap.spring.service.MoviesService;

@Controller
public class RedisController {

    private final MoviesService moviesService;

    public RedisController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/movies";
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        model.addAttribute("moviescount", moviesService.getMoviesCount());
        model.addAttribute("movies", moviesService.getMoviesList());
        return "movies";
    }

    @PostMapping("/movies")
    public String postMessage(@RequestParam String name,
                              @RequestParam String year,
                              @RequestParam String actors) {

        moviesService.addMovie(name, year, actors);
        return "redirect:/movies";
    }

    @GetMapping("/form")
    public String messageForm() {
        return "movieForm";
    }
}
