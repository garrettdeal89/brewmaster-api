package com.gdeal.brewmaster.dto;

import com.gdeal.brewmaster.model.CoffeeType;
import java.util.Set;
import io.swagger.v3.oas.annotations.media.Schema;

//Documentation for RecipeDTO 
@Schema(
    description = "Represents a coffee recipe returned by the API."
)

//DTO for Recipe
public class RecipeDTO {
    
    @Schema(
    description = "Unique identifier of the recipe",
    example = "1")
    private Long id;
    
    @Schema(
    description = "Type of coffee beverage"
    )
    private CoffeeType type;
    
    @Schema(
    description = "Recipe name",
    example = "Latte"
    )
    private String name;
    
    @Schema(
    description = "Short description of the recipe",
    example = "Espresso with steamed milk."
    )
    private String description;

    @Schema(
    description = "List of ingredients used in the recipe",
    example = "[\"Espresso\", \"Milk\"]"
    )
    private Set<String> ingredients;

    public RecipeDTO(
        Long id,
        CoffeeType type,
        String name,
        String description,
        Set<String> ingredients) {

    this.id = id;
    this.type = type;
    this.name = name;
    this.description = description;
    this.ingredients = ingredients;
}


public RecipeDTO(
        Long id,
        CoffeeType type,
        String name,
        String description) {

    this.id = id;
    this.type = type;
    this.name = name;
    this.description = description;
    this.ingredients = Set.of();
    }

    public Long getId() {
        return id;
    }

    public CoffeeType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }
}
