package edu.ap.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "web_user")
public class WebUser {
    
    @Id
    @GeneratedValue
    @Column(name = "col_id")
    private Long id;

    @Column(name = "col_username", nullable = false, unique = true)
    private String username;

    @Column(name = "col_password", nullable = false)
    private String password;

    @Column(name = "col_role")
    private String role;

    protected WebUser() {
        // required by JPA
    }

    public WebUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}
