package com.gdeal.brewmaster.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
    description = "Request body used to register a new user."
)
public class RegisterRequest {

    @NotBlank(
        message = "Username is required."
    )
    @Size(
        min = 3,
        max = 50,
        message = "Username must be 3-50 characters.")
    @Schema(example = "jsmith")
    private String username;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be valid.")
    @Schema(example = "jsmith@gmail.com")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(
        min = 8,
        max = 21,
        message = "Password must be 8-21 characters."
    )
    @Schema(example = "UniquePassword321!")
    private String password;

    //Constructor
    public RegisterRequest() {}

    //Getters and Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
