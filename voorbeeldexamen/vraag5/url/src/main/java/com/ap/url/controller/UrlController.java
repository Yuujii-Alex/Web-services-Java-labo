package com.ap.url.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UrlController {

    @GetMapping("/")
    public String getIndex() {
        return "redirect:/exam";
    }

    @GetMapping("/exam/**")
    public String getExam(Model model, HttpServletRequest request) {

        String url = request.getRequestURI();
        url = url.replaceFirst("/exam", "");

        String[] parts = url.split("/");
        List<String> validParts = new ArrayList<>();

        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                validParts.add(part);
            }
        }

        String joinedResult = String.join(" -- ", validParts);

        // Geef de kant-en-klare tekst mee naar HTML
        model.addAttribute("resultString", joinedResult);
        return "url";
    }

}
