package com.gdeal.brewmaster.repository;

import com.gdeal.brewmaster.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    
}
