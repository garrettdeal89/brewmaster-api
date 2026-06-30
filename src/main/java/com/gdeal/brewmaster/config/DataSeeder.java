package com.gdeal.brewmaster.config;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import com.gdeal.brewmaster.model.CoffeeType;
import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.repository.RecipeRepository;




@Component
public class DataSeeder implements CommandLineRunner {

    private final RecipeRepository recipeRepository;

    public DataSeeder(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

@Override
    public void run(String... args) {
        
        // Seed data here
        if (recipeRepository.count() == 0) {
            
            // Add your initial data seeding logic here
            recipeRepository.save(new Recipe(
                null,
                CoffeeType.AMERICANO,
                "Americano",
                "Espresso with hot water."

            ));

            recipeRepository.save(new Recipe(
                null,
            CoffeeType.CAPPUCCINO,
                "Cappuccino",
                "Equal parts espresso, steamed milk, and milk foam."
            ));

            recipeRepository.save(new Recipe(
                null,
                CoffeeType.LATTE,
                "Latte",
                "Espresso with steamed milk."
            ));

        }
    }
}
