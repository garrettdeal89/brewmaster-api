package com.gdeal.brewmaster.specification;

import com.gdeal.brewmaster.model.Recipe;
import org.springframework.data.jpa.domain.Specification;
import com.gdeal.brewmaster.model.CoffeeType;


public class RecipeSpecifications {

    // Private constructor to prevent instantiation
    private RecipeSpecifications() {
        
    }

    // Specification to filter recipes by name (case-insensitive, partial match)
    public static Specification<Recipe> nameContains(String name) {

        return (root, query, builder) -> builder.like(
            builder.lower(root.get("name")), 
            "%" + name.toLowerCase() + "%"
        );
    }

    public static Specification<Recipe> hasType(CoffeeType type) {
        return (root, query, builder) -> builder.equal(root.get("type"), type);
    }
}
