package com.gdeal.brewmaster.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateIngredientRequest {
    
    @NotBlank(message = "Ingredient name is reuqired.")
    private String name;

    // Constructors
    public CreateIngredientRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
