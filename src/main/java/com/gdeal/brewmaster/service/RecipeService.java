package com.gdeal.brewmaster.service;

import com.gdeal.brewmaster.dto.CreateRecipeRequest;
import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.dto.RecipeQueryParams;
import com.gdeal.brewmaster.exception.RecipeNotFoundException;
import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.repository.RecipeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class RecipeService {

   private final RecipeRepository recipeRepository;

   private static final Set<String> ALLOWED_SORT_FIELDS =
        Set.of("id", "name", "type");

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Page<RecipeDTO> getAllRecipes(
        RecipeQueryParams params) {

    if (!ALLOWED_SORT_FIELDS.contains(params.getSortField())) {
    throw new IllegalArgumentException(
            "Invalid sort field: " + params.getSortField()
            + ". Allowed values are: " + ALLOWED_SORT_FIELDS);
}
    Sort sort = params.getSortDirection().equalsIgnoreCase("desc")
            ? Sort.by(params.getSortField()).descending()
            : Sort.by(params.getSortField()).ascending();

    Pageable pageable = PageRequest.of(params.getPage(), params.getSize(), sort);

    Page<Recipe> recipes;

        if (params.getType() != null) {
           recipes = recipeRepository.findByType(params.getType(), pageable);
        } else {
          recipes = recipeRepository.findAll(pageable);
        }

return recipes.map(this::toDTO);

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
