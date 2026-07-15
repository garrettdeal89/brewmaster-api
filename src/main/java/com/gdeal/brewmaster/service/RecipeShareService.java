package com.gdeal.brewmaster.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gdeal.brewmaster.dto.RecipeShareDTO;
import com.gdeal.brewmaster.exception.RecipeNotFoundException;
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

    
    @Transactional
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

    private RecipeShareDTO toDTO(RecipeShare recipeShare) {

        return new RecipeShareDTO(

            recipeShare.getToken(),
            SHARED_RECIPE_PATH + recipeShare.getToken(),
            recipeShare.getCreatedAt()
        );
    }

}
