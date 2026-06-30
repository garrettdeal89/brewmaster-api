package com.gdeal.brewmaster.repository;

import org.springframework.stereotype.Repository;

import com.gdeal.brewmaster.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
}
