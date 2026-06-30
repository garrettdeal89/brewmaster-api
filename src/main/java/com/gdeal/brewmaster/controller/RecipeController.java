package com.gdeal.brewmaster.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gdeal.brewmaster.service.RecipeService;


import com.gdeal.brewmaster.model.Recipe;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {

        return recipeService.getAllRecipes();
    }
}
