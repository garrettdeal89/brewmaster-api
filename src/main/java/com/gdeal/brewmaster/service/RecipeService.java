package com.gdeal.brewmaster.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.repository.RecipeRepository;



@Service
public class RecipeService {

  private final RecipeRepository recipeRepository;

  public RecipeService(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  } 
}
