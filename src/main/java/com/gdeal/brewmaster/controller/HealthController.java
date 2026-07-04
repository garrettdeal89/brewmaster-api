package com.gdeal.brewmaster.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdeal.brewmaster.dto.HealthResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;


@Tag(
    name = "Health",
    description = "Endpoint for checking if the Brewmaster API is responsive and running."
)
@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public HealthResponse health() {
        return new HealthResponse(
            "Running", 
            "Brewmaster-API", 
         LocalDateTime.now()
        );
    }
}