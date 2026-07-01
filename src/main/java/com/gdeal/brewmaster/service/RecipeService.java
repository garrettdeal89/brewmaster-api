package com.gdeal.brewmaster.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.repository.RecipeRepository;
import com.gdeal.brewmaster.dto.RecipeDTO;




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

    private RecipeDTO toDTO(Recipe recipe) {
        return new RecipeDTO(
                recipe.getId(),
                recipe.getType(),
                recipe.getName(),
                recipe.getDescription()
        );
    }
}
