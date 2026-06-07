package com.ap.jpa.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ap.jpa.entity.Check;

@Repository
public interface CheckRepository extends ListCrudRepository<Check, Long> {
    
}
