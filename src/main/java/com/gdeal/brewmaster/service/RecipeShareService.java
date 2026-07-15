package com.gdeal.brewmaster.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.dto.RecipeShareDTO;
import com.gdeal.brewmaster.exception.RecipeNotFoundException;
import com.gdeal.brewmaster.exception.ResourceNotFoundException;
import com.gdeal.brewmaster.model.Ingredient;
import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.model.RecipeShare;
import com.gdeal.brewmaster.repository.RecipeRepository;
import com.gdeal.brewmaster.repository.RecipeShareRepository;

import jakarta.transaction.Transactional;

@Service
public class RecipeShareService {
    
    private static final String SHARED_RECIPE_PATH = "/api/shared-recipes/";
    
    private final RecipeRepository recipeRepository;
    private final RecipeShareRepository recipeShareRepository;

    public RecipeShareService(

        RecipeRepository recipeRepository,
        RecipeShareRepository recipeShareRepository) {

            this.recipeRepository = recipeRepository;
            this.recipeShareRepository = recipeShareRepository;
        }

    
    @org.springframework.transaction.annotation.Transactional
    public RecipeShareDTO createShareLink(Long recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() ->
        new RecipeNotFoundException(recipeId)
    );

    RecipeShare recipeShare = new RecipeShare(

        UUID.randomUUID(),
        recipe,
        LocalDateTime.now()
    );

    RecipeShare savedShare = recipeShareRepository.save(recipeShare);

    return toDTO(savedShare);
    }
    
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
public RecipeDTO getSharedRecipe(UUID token) {

    RecipeShare recipeShare = recipeShareRepository.findByToken(token).orElseThrow(() ->

    new ResourceNotFoundException(

        "Recipe Share",
        token
    )
    );

    return toRecipeDTO(recipeShare.getRecipe());
}




    private RecipeShareDTO toDTO(RecipeShare recipeShare) {

        return new RecipeShareDTO(

            recipeShare.getToken(),
            SHARED_RECIPE_PATH + recipeShare.getToken(),
            recipeShare.getCreatedAt()
        );
    }

    private RecipeDTO toRecipeDTO(Recipe recipe) {

        return new RecipeDTO(

            recipe.getId(),
            recipe.getType(),
            recipe.getName(),
            recipe.getDescription(),
            recipe.getBrewMethod() != null ? recipe.getBrewMethod().getName() : null,
            recipe.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toSet())
        );
    }

}
