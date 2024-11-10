package com.example.microservice.DTO;

import java.util.Set;

public class UserDTO {

    private String id;
    private String username;
    private String email;
    private Set<String> roles;
    private boolean enabled;
    private boolean active;

    // Constructeur par défaut
    public UserDTO() {
    }

    // Constructeur avec paramètres pour initialiser tous les champs
    public UserDTO(String id, String username, String email, Set<String> roles, boolean enabled, boolean active) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.enabled = enabled;
        this.active = active;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
