package edu.ap.spring.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ap.spring.dao.WebUserRepository;
import edu.ap.spring.entity.WebUser;

@Service
public class WebUserService {
    
    private final WebUserRepository repository;
    private final PasswordEncoder encoder;

    public WebUserService(WebUserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Optional<WebUser> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public boolean usernameExists(String username) {
        return repository.findByUsername(username).isPresent();
    }

    public WebUser save(String username, String password, String role) {
        WebUser user = new WebUser(username, encoder.encode(password), "ROLE_" + role);
        return repository.save(user);
    }
}
