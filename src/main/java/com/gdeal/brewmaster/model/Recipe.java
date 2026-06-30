package com.gdeal.brewmaster.model;

public class Recipe {
    
    private long id;
    private CoffeeType type;
    private String name;
    private String description;

    public Recipe(long id, CoffeeType type, String name, String description) {
        
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
