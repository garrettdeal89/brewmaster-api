package com.gdeal.brewmaster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

import com.gdeal.brewmaster.model.CoffeeType;

import io.swagger.v3.oas.annotations.media.Schema;

//Documentation for CreateRecipeRequest
@Schema(
    description = "Request body used to create or update a coffee recipe."
)
public class CreateRecipeRequest {
    

    @NotNull
    @Schema(
    description = "Coffee beverage type"
    )
    private CoffeeType type;

    @NotBlank
    @Schema(
    description = "Recipe name",
    example = "Flat White"
    )
    private String name;

    @NotBlank
    @Schema(
    description = "Description of the recipe",
    example = "Espresso with steamed milk."
    )
    private String description;

    
    private Set<Long> ingredientIds;

    public CreateRecipeRequest(){    
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getIngredientIds(){
        return ingredientIds;
    }

    public void setIngredientIds(Set<Long> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }
}
