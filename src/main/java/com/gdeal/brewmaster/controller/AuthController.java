package com.gdeal.brewmaster.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdeal.brewmaster.dto.ApiResponse;
import com.gdeal.brewmaster.dto.RegisterRequest;
import com.gdeal.brewmaster.dto.UserAccountDTO;
import com.gdeal.brewmaster.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@Tag(
    name = "authentication",
    description = "Endpoints for user registration and authentication."
)
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    @Operation(summary = "Register a new user.")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserAccountDTO>> register(

        @Valid
        @RequestBody
        RegisterRequest request) {

            UserAccountDTO registeredUser = authService.register(request);
        
            ApiResponse<UserAccountDTO> response = new ApiResponse<>(

                HttpStatus.CREATED.value(),
                "User registered successfully.",
                registeredUser
            );

            return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response
            );
        }
}
