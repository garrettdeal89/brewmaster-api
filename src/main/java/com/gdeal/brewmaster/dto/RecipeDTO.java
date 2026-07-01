package com.gdeal.brewmaster.dto;

import com.gdeal.brewmaster.model.CoffeeType;


public class RecipeDTO {
    
    private long id;
    private CoffeeType type;
    private String name;
    private String description;

    public RecipeDTO(Long id, CoffeeType type, String name, String description) {

        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public long getId() {
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
}
