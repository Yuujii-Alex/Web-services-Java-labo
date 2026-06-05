package com.ap.intro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ap.intro.service.GradeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {
    private final GradeService service;

    public GradeController(GradeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/grades";
    }

    @GetMapping("/grades")
    public String grades(Model model) {
        model.addAttribute("grades", service.getAllGrades());
        return "grades";
    }

    @GetMapping("/grade/{firstName}/{lastName}")
    public String detail(Model model, @PathVariable String firstName, @PathVariable String lastName) {
        model.addAttribute("grade", service.getGradeByName(firstName, lastName));
        return "grade";
    }

    @GetMapping("/grade")
   public String grade() {
      return "gradeForm";
   }

    @PostMapping("/grade")
    public String addGrade(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int grade) {
        service.addGrade(firstName, lastName, grade);
        return "redirect:/grades";
    }

}
