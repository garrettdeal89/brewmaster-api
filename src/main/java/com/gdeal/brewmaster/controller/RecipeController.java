package com.gdeal.brewmaster.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.model.CoffeeType;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    
    @GetMapping
    public List<Recipe> getAllRecipes() {

        // Logic to retrieve all recipes
        return List.of(
            new Recipe(1, CoffeeType.AMERICANO, "Americano", "Espresso with hot water."),
            new Recipe(2, CoffeeType.CAPPUCCINO, "Cappuccino", "Equal parts espresso, milk, and milk foam."),
            new Recipe(3, CoffeeType.LATTE, "Latte", "Espresso with steamed milk.")
        );
    }
}
