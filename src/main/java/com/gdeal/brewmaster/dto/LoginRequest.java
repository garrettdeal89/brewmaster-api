package com.gdeal.brewmaster.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(
    description = "User authentication credentials."
)
public class LoginRequest {
    
    @NotBlank(message = "User name is required.")
    @Schema(example = "jsmith")
    private String username;

    @NotBlank(message = "Password is required.")
    @Schema(example = "uniquePassword321!")
    private String password;

    public LoginRequest() {}

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
}
