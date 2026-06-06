package edu.ap.spring.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.ap.spring.dao.WebUserRepository;
import edu.ap.spring.entity.WebUser;

@Service
public class WebUserService {
    
    private final WebUserRepository repository;

    public WebUserService(WebUserRepository repository) {
        this.repository = repository;
    }

    public Optional<WebUser> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public boolean usernameExists(String username) {
        return repository.findByUsername(username).isPresent();
    }

    public WebUser save(String username, String password, String role) {
        // Plaintext password for lab purposes since Security is removed
        WebUser user = new WebUser(username, password, role);
        return repository.save(user);
    }
}
