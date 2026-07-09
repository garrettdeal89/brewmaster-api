package com.gdeal.brewmaster.controller;

import com.gdeal.brewmaster.dto.ApiResponse;
import com.gdeal.brewmaster.dto.BrewMethodDTO;
import com.gdeal.brewmaster.service.BrewMethodService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brew-methods")
@Tag(
    name = "Brew Methods",
    description = "Endpoints for retrieving supported coffee brew methods."
)
public class BrewMethodController {

    private final BrewMethodService brewMethodService;

    public BrewMethodController(BrewMethodService brewMethodService) {
        this.brewMethodService = brewMethodService;
    }

@Operation(summary = "Get all brew methods")
@io.swagger.v3.oas.annotations.responses.ApiResponses({
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "200",
        description = "Brew methods retrieved successfully"
    )
})
@GetMapping
public ResponseEntity<ApiResponse<List<BrewMethodDTO>>> getAllBrewMethods() {

    return ResponseEntity.ok(
        new ApiResponse<>(
            200,
            "Brew methods retrieved successfully.",
            brewMethodService.getAllBrewMethods()
        )
    );
    }
}
