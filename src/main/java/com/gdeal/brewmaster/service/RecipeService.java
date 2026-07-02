package com.gdeal.brewmaster.service;

import com.gdeal.brewmaster.dto.CreateRecipeRequest;
import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.exception.RecipeNotFoundException;
import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.model.CoffeeType;
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
        int page,
        int size,
        String sortField,
        String sortDirection,
        CoffeeType type) {

    if (!ALLOWED_SORT_FIELDS.contains(sortField)) {
    throw new IllegalArgumentException(
            "Invalid sort field: " + sortField +
            ". Allowed values are: " + ALLOWED_SORT_FIELDS);
          }

    Sort sort = sortDirection.equalsIgnoreCase("desc")
            ? Sort.by(sortField).descending()
            : Sort.by(sortField).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    Page<Recipe> recipes;

        if (type != null) {
           recipes = recipeRepository.findByType(type, pageable);
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
