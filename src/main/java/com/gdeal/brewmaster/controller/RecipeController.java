package com.gdeal.brewmaster.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gdeal.brewmaster.service.RecipeService;
import com.gdeal.brewmaster.dto.RecipeDTO;
import org.springframework.web.bind.annotation.PathVariable;
import com.gdeal.brewmaster.dto.CreateRecipeRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.net.URI;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipeById(@PathVariable Long id) {
        
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(
        @Valid @RequestBody CreateRecipeRequest request) {

    RecipeDTO recipe = recipeService.createRecipe(request);

    return ResponseEntity
            .created(URI.create("/api/recipes/" + recipe.getId()))
            .body(recipe);
    }
}
