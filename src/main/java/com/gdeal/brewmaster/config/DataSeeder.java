package com.gdeal.brewmaster.config;

import com.gdeal.brewmaster.model.CoffeeType;
import com.gdeal.brewmaster.model.Ingredient;
import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.repository.IngredientRepository;
import com.gdeal.brewmaster.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public DataSeeder(
            RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository) {

        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) {

        // Seed ingredients
        if (ingredientRepository.count() == 0) {

            ingredientRepository.saveAll(List.of(
                    new Ingredient("Espresso"),
                    new Ingredient("Milk"),
                    new Ingredient("Hot Water"),
                    new Ingredient("Milk Foam"),
                    new Ingredient("Vanilla Syrup"),
                    new Ingredient("Chocolate Syrup"),
                    new Ingredient("Caramel Syrup"),
                    new Ingredient("Whipped Cream"),
                    new Ingredient("Ice")
            ));

            System.out.println("Seeded ingredients.");
        }

        // Seed recipes
        if (recipeRepository.count() == 0) {

            Ingredient espresso =
                    ingredientRepository.findByNameIgnoreCase("Espresso").orElseThrow();

            Ingredient milk =
                    ingredientRepository.findByNameIgnoreCase("Milk").orElseThrow();

            Ingredient hotWater =
                    ingredientRepository.findByNameIgnoreCase("Hot Water").orElseThrow();

            Ingredient milkFoam =
                    ingredientRepository.findByNameIgnoreCase("Milk Foam").orElseThrow();

            recipeRepository.save(new Recipe(
                    null,
                    CoffeeType.AMERICANO,
                    "Americano",
                    "Espresso with hot water.",
                    Set.of(espresso, hotWater)
            ));

            recipeRepository.save(new Recipe(
                    null,
                    CoffeeType.CAPPUCCINO,
                    "Cappuccino",
                    "Equal parts espresso, steamed milk, and milk foam.",
                    Set.of(espresso, milk, milkFoam)
            ));

            recipeRepository.save(new Recipe(
                    null,
                    CoffeeType.LATTE,
                    "Latte",
                    "Espresso with steamed milk.",
                    Set.of(espresso, milk)
            ));

            System.out.println("Seeded recipes.");
        }
    }
}
