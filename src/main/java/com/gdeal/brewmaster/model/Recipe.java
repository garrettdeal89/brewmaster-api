package com.gdeal.brewmaster.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Recipe {
    
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CoffeeType type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
        name = "recipe_ingredients",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();


    // Backwards-compatible constructor
    public Recipe(
            Long id,
            CoffeeType type,
            String name,
            String description) {

        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.ingredients = new HashSet<>();
    }


    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}
