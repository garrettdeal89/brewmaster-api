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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
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


    @ManyToOne
    @JoinColumn(name = "brew_method_id")
    private BrewMethod brewMethod;


    @ManyToMany
    @JoinTable(
        name = "recipe_ingredients",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    // Constructors
    public Recipe() {
    }

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


    public Recipe(
            Long id,
            CoffeeType type,
            String name,
            String description,
            Set<Ingredient> ingredients) {

        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public BrewMethod getBrewMethod() {
        return brewMethod;
    }

    public void setBrewMethod(BrewMethod brewMethod) {
        this.brewMethod = brewMethod;
    }


    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
