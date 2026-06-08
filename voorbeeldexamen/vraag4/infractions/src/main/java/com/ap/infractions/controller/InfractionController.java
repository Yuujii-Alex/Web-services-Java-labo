package com.ap.infractions.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ap.infractions.jpa.Infraction;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InfractionController {

    @Value("classpath:static/infractions.json")
    public InputStream data;

    private List<Infraction> infractions = new ArrayList();

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/infractions")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/infractions/{aantal}")
    public String aantalInfractions(@PathVariable int aantal, Model model) throws JsonParseException, JsonMappingException, IOException {

        if (infractions.size() < 1){
            infractions = Arrays.asList(objectMapper.readValue(data, Infraction[].class));
        }

        List<Infraction> results = new ArrayList<>();

        for (Infraction infraction : infractions) {
            try {
                if (Integer.parseInt(infraction.infractions_speed) >= aantal)
                    results.add(infraction);
            } catch (Exception ex) {
            }
        }

        Collections.sort(results);

        model.addAttribute("infractions", results);
        return "infractionsResult";
    }

    @GetMapping("/infractions/form")
    public String getForm() {
        return"infractionsForm";
    }
    
    @PostMapping("/year")
    public String infractionsFromYear(@RequestParam("year") int year, Model model) throws JsonParseException, JsonMappingException, IOException{
        if (infractions.size() < 1){
            infractions = Arrays.asList(objectMapper.readValue(data, Infraction[].class));
        }

        List<Infraction> results = new ArrayList<>();

        for (Infraction infraction : infractions) {
            try{
                if (Integer.parseInt(infraction.year) == year){
                    results.add(infraction);
                }
            }
            catch(Exception ex){}  
        }

        Collections.sort(results);

        model.addAttribute("infractions", results);

        return "infractionsResult";
    }
    
}
