package com.ap.students.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ap.students.entity.Student;

@Repository
public interface StudentRepository extends ListCrudRepository<Student, Long> {
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
    List<Student> findByOrderByLastNameAsc(); // geeft alle studenten gesorteerd
}
