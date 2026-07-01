package com.gdeal.brewmaster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.gdeal.brewmaster.model.CoffeeType;


public class CreateRecipeRequest {
    
    @NotNull
    private CoffeeType type;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

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
}
