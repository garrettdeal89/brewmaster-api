package com.gdeal.brewmaster.controller;

import java.net.URI;
import java.rmi.server.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdeal.brewmaster.dto.ApiResponse;
import com.gdeal.brewmaster.dto.RecipeShareDTO;
import com.gdeal.brewmaster.service.RecipeShareService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(
    name = "Recipe Sharing",
    description = "Endpoints for creatienf and retrieving recipe share links."
)
public class RecipeShareController {
    
    private final RecipeShareService recipeShareService;

    public RecipeShareController(RecipeShareService recipeShareService) {

        this.recipeShareService = recipeShareService;
    }

    @io.swagger.v3.oas.annotations.Operation (summary = "Create a public share link for a recipe")
    @PostMapping("/recipes/{recipeId}/share")
    public ResponseEntity<ApiResponse<RecipeShareDTO>> createShareLink(

        @PathVariable Long recipeId) {

            RecipeShareDTO share = recipeShareService.createShareLink(recipeId);

            ApiResponse<RecipeShareDTO> response = new ApiResponse<>(

                HttpStatus.CREATED.value(),
                "Recipe share link created successfully",
                share
            );

            return ResponseEntity
                        .created(URI.create(share.getSharePath()))
                        .body(response);
        }
}
