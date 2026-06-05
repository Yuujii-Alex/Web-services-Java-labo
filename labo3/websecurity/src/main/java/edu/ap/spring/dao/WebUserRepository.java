package edu.ap.spring.dao;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import edu.ap.spring.entity.WebUser;

@Repository
public interface WebUserRepository extends ListCrudRepository<WebUser, Long> {
    Optional<WebUser> findByUsername(String username);
}
