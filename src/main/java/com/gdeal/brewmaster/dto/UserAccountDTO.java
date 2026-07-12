package com.gdeal.brewmaster.dto;

import com.gdeal.brewmaster.model.Role;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "Safe user information returned by the API."
)
public class UserAccountDTO {
    
    private Long id;
    private String username;
    private String email;
    private Role role;

    public UserAccountDTO(
        Long id,
        String username,
        String email,
        Role role) {

            this.id = id;
            this.username = username;
            this.email = email;
            this.role = role;
        }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
