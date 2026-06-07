package com.ap.students.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ap.students.controller.StudentDTO;
import com.ap.students.dao.StudentRepository;
import com.ap.students.entity.Student;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<StudentDTO> getStudents() {
        return repo.findByOrderByLastNameAsc().stream().map(this::assemble).toList();
    }

    public void createStudent(String firstName, String lastName, LocalDate dateOfBirth, String studyProgram) {
        if (!repo.findByFirstNameAndLastName(firstName, lastName).isPresent()) {
            repo.save(new Student(firstName, lastName, dateOfBirth, studyProgram));
        }
    }

    private StudentDTO assemble(Student student) {
        return new StudentDTO(student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getStudyProgram());
    }
    
}
