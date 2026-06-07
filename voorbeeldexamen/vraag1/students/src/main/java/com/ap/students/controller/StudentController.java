package com.ap.students.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ap.students.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class StudentController {
    
    private final StudentService service;

    public StudentController(StudentService studentService) {
        this.service = studentService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String getStudents(Model model) {
        model.addAttribute("students", service.getStudents());
        return "students";
    }

    @GetMapping("/studentForm")
    public String studentForm() {
        return "studentForm";
    }
    
    
    @PostMapping("/studentForm")
    public String addStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam LocalDate dateOfBirth, @RequestParam String studyProgram) {
        service.createStudent(firstName, lastName, dateOfBirth, studyProgram);
        
        return "redirect:/students";
    }
    
    
}
