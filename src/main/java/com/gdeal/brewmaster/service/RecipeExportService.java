package com.gdeal.brewmaster.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.exception.RecipeExportException;

@Service
public class RecipeExportService {

    private final RecipeService recipeService;
    private final ObjectMapper objectMapper;

    public RecipeExportService(

        RecipeService recipeService,
        ObjectMapper objectMapper) {

            this.recipeService = recipeService;
            this.objectMapper = objectMapper;
        }
    
        public byte[] exportRecipeAsJson(Long recipeId) {

            RecipeDTO recipe = recipeService.getRecipeById(recipeId);

            try {

                return objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsBytes(recipe);
            } catch (JsonProcessingException ex) {

                throw new RecipeExportException(
                    recipeId,
                    ex
                );
            }
        }
}
