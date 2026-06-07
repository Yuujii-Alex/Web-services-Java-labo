package com.ap.jpa.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ap.jpa.service.CheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.temporal.ChronoUnit;



@Controller
public class CheckController {
    
    private final CheckService service;

    public CheckController(CheckService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/checks";
    }

    @GetMapping("/checks")
    public String checks(Model model) {
        model.addAttribute("checks", service.getChecks());
        return "checks";
    }

    @GetMapping("/check")
    public String check() {
        return "check";
    }

    @PostMapping("/check")
    public String addCheck(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateToCheck, 
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate, 
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate) {
        

        // als de date voor de start datum is of na de start datum
        boolean isChecked = !dateToCheck.isBefore(startDate) && !dateToCheck.isAfter(endDate);

        // check hoeveel dagen er zijn tussen nu en de date to check
        int days = (int) Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), dateToCheck));
        
        service.addCheck(dateToCheck, isChecked, days);
        return "redirect:/checks";
    }
    
    
    
    
}
