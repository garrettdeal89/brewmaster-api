package com.gdeal.brewmaster.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipe_shares")
public class RecipeShare {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID token;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public RecipeShare() {}

    public RecipeShare (

        UUID token,
        Recipe recipe,
        LocalDateTime createdAt) {

            this.token = token;
            this.recipe = recipe;
            this.createdAt = createdAt;
        }

        public Long getId() {

            return id;
        }

        public UUID getToken() {

            return token;
        }

        public Recipe getRecipe() {

            return recipe;
        }

        public LocalDateTime getCreatedAt() {

            return createdAt;
        }
}
