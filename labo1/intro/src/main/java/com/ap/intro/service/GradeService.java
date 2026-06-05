package com.ap.intro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ap.intro.controller.GradeDTO;
import com.ap.intro.dao.GradeRepository;
import com.ap.intro.entity.Grade;

@Service
public class GradeService {
    private final GradeRepository repository;

    public GradeService(GradeRepository repository) {
        this.repository = repository;
    }

    public List<GradeDTO> getAllGrades() {
        return repository.findAll().stream().map(this::assemble).toList();
    }
    
    public GradeDTO getGradeByName(String firstName, String lastName) {
        return assemble(repository.findGradeByFirstNameAndLastName(firstName, lastName));
    }

    public void addGrade(String firstName, String lastName, int grade) {
        repository.save(new Grade(firstName, lastName, grade));
    }


    private GradeDTO assemble(Grade grade) {
        return new GradeDTO(
            grade.getFirstName(),
            grade.getLastName(),
            grade.getGrade()
        );
    }
}
