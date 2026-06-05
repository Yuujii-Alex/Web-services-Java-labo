package com.ap.rest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ap.rest.service.InfractionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class InfractionThymeleafController {
    
    private final InfractionService service;

    public InfractionThymeleafController(InfractionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/year")
    public String searchByYear(@RequestParam Integer year, Model model) {
        List<InfractionDTO> result = service.findByYear(year);
        model.addAttribute("infractions", result);
        return "year";
    }
    
    @GetMapping("/detail/{id}")
    public String searchById(@PathVariable Long id, Model model) {
        InfractionDTO result = service.findById(id);
        model.addAttribute("infraction", result);
        return "detail";
    }
    
    @GetMapping("/search")
    public String openForm() {
        return "searchByYear";
    }    

}
