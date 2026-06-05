package com.ap.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap.rest.service.InfractionService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/rest")
public class InfractionRestController {
    private final InfractionService service;

    public InfractionRestController(InfractionService service) {
        this.service = service;
    }

    @GetMapping("/year")
    public ResponseEntity<List<InfractionDTO>> searchByYear(@RequestParam Integer year) {
        List<InfractionDTO> result = service.findByYear(year);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/detail")
    public ResponseEntity<InfractionDTO> getDetail(@RequestParam Long id) {
        InfractionDTO result = service.findById(id);
        return ResponseEntity.ok(result);
    }
}
