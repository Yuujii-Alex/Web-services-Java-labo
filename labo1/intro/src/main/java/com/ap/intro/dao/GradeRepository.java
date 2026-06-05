package com.ap.intro.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ap.intro.entity.Grade;

@Repository
public interface GradeRepository extends ListCrudRepository<Grade, Long> {
    Grade findGradeByFirstNameAndLastName(String firstName, String lastName);
}
