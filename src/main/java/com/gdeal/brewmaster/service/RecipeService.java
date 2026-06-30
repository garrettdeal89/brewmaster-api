package com.gdeal.brewmaster.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.model.CoffeeType;


@Service
public class RecipeService {

    public List<Recipe> getAllRecipes() {
        
        return List.of(
            
            new Recipe(
                1L, 
                CoffeeType.AMERICANO, 
                "Americano", 
                "Espresso and hot water."
            ),
            
            
            new Recipe(
                2L, 
                CoffeeType.CAPPUCCINO, 
                "Cappuccino", 
                "Equal parts espresso, steamed milk, and milk foam."
            ),
            
            
            new Recipe(
                3L, 
                CoffeeType.LATTE, 
                "Latte", 
                "Espresso with steamed milk."
            )
        );
    }
    
}
