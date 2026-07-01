package com.gdeal.brewmaster.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.repository.RecipeRepository;
import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.exception.RecipeNotFoundException;
import com.gdeal.brewmaster.dto.CreateRecipeRequest;


@Service
public class RecipeService {

   private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public RecipeDTO getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
        
                return toDTO(recipe);
    }

    public RecipeDTO createRecipe(CreateRecipeRequest request) {
    Recipe recipe = new Recipe();

    updateRecipeFromRequest(recipe, request);

    Recipe savedRecipe = recipeRepository.save(recipe);
    
    return toDTO(savedRecipe);
}

    public RecipeDTO updateRecipe(Long id, CreateRecipeRequest request) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));

        updateRecipeFromRequest(recipe, request);

        Recipe updatedRecipe = recipeRepository.save(recipe);
        
        return toDTO(updatedRecipe);

    }

    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
        
        recipeRepository.delete(recipe);
    }
    

    private void updateRecipeFromRequest(
      Recipe recipe, 
      CreateRecipeRequest request) {

        recipe.setType(request.getType());
        recipe.setName(request.getName());
        recipe.setDescription(request.getDescription());
    }


    private RecipeDTO toDTO(Recipe recipe) {
        return new RecipeDTO(
                recipe.getId(),
                recipe.getType(),
                recipe.getName(),
                recipe.getDescription()
        );
    }
}
