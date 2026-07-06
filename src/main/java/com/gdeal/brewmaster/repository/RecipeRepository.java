package com.gdeal.brewmaster.repository;

import org.springframework.stereotype.Repository;

import com.gdeal.brewmaster.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.gdeal.brewmaster.model.CoffeeType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, 
    JpaSpecificationExecutor<Recipe> {

    Page<Recipe> findByType(
        CoffeeType type, 
        Pageable pageable
    );
}
