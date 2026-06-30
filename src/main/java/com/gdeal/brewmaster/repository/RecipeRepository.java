package com.gdeal.brewmaster.repository;
import java.util.List;
import com.gdeal.brewmaster.model.Recipe;


public interface RecipeRepository {
    
    List<Recipe> findAll();
}
