package com.ap.messaging.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ap.messaging.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class RedisController {
    
    private static final String CHANNEL = "edu:ap:redis";

    private final List<String> redisMessages = new ArrayList<>();
    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/messages";
    }

    @GetMapping("/messages")
    public String messages(Model model) {
        model.addAttribute("messages", redisMessages);
        return "messages";
    }
    
    @PostMapping("/messages")
    public String messages(@RequestParam String message) {
        redisService.sendMessage(CHANNEL, message);
        return "redirect:/messages";
    }

    @GetMapping("/form")
    public String messageForm() {
        return "messageForm";
    }
    
    public void onMessage(String message) {
        this.redisMessages.add(message);
    }
}
