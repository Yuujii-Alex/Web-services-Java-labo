package com.ap.rest.dao;

import java.util.List;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.repository.ListCrudRepository;

import com.ap.rest.entity.Infraction;

@Persistent
public interface InfractionRepository extends ListCrudRepository<Infraction, Long>{
    List<Infraction> findByYear(Integer year);
}
