package com.gdeal.brewmaster.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdeal.brewmaster.service.RecipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.dto.RecipeQueryParams;

import org.springframework.web.bind.annotation.PathVariable;

import com.gdeal.brewmaster.dto.ApiResponse;
import com.gdeal.brewmaster.dto.CreateRecipeRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.data.domain.Page;


@Tag(
    name = "Recipes",
    description = "Endpoints for managing coffee recipes, including: creation, retrieval, updating, deletion, pagination, sorting, and filtering."
)
@RestController
@RequestMapping("/api/recipes")
@Validated
public class RecipeController {
    
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(
        summary = "Retrieve all recipes.", 
        description = """
        Returns a paginated list of coffee recipes.

        Results can be:
        • Paginated
        • Sorted by id, name, or type
        • Filtered by coffee type
        """)
    @ApiResponses({
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "200",
        description = "Recipes retrieved successfully"
    ),
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "400",
        description = "Invalid query parameters"
    )
})

// GET all recipes with pagination and sorting
    @GetMapping
    public ResponseEntity<ApiResponse<Page<RecipeDTO>>> getAllRecipes(
        @Valid RecipeQueryParams params) {

    Page<RecipeDTO> recipes = recipeService.getAllRecipes(params);

    ApiResponse<Page<RecipeDTO>> response =
            new ApiResponse<>(200, "Recipes retrieved successfully", recipes);

    return ResponseEntity.ok(response);
}
// GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RecipeDTO>> getRecipeById(@PathVariable Long id) {
        
        RecipeDTO recipe = recipeService.getRecipeById(id);

    return ResponseEntity.ok(
            new ApiResponse<>(200, "Recipe retrieved successfully", recipe)
        );
    }

    // POST to create a new recipe
    @PostMapping
    public ResponseEntity<ApiResponse<RecipeDTO>> createRecipe(
        @Valid @RequestBody CreateRecipeRequest request) {

    RecipeDTO recipe = recipeService.createRecipe(request);

    return ResponseEntity
            .created(URI.create("/api/recipes/" + recipe.getId()))
            .body(new ApiResponse<>(201, "Recipe created successfully", recipe));
    }

    // PUT to update an existing recipe
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RecipeDTO>> updateRecipe(
        @PathVariable Long id,
        @Valid @RequestBody CreateRecipeRequest request) {

    RecipeDTO recipe = recipeService.updateRecipe(id, request);

    return ResponseEntity.ok(
            new ApiResponse<>(200, "Recipe updated successfully", recipe)
        );
    }

    //DELETE to remove a recipe
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRecipe(@PathVariable Long id) {
        
        recipeService.deleteRecipe(id);

    return ResponseEntity.ok(
            new ApiResponse<>(200, "Recipe deleted successfully", null)
        );
    }
}
