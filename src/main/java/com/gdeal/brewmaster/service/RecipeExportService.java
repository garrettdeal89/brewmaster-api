package com.gdeal.brewmaster.service;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.dto.RecipeExportResult;
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
    
        public RecipeExportResult exportRecipeAsJson(Long recipeId) {

            RecipeDTO recipe = recipeService.getRecipeById(recipeId);

            try {

                byte[] content = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsBytes(recipe);

                String filename = createFilename(recipe.getName(), recipeId);

                return new RecipeExportResult(
                    filename,
                    content
            );
            
            } catch (JsonProcessingException ex) {

                throw new RecipeExportException(

                    recipeId,
                    ex
                );
            }
        }

        private String createFilename(

            String recipeName,
            Long recipeId) {

                String safeName = recipeName == null ? ""
                    : recipeName
                        .toLowerCase(Locale.ROOT)
                        .replaceAll("[^a-z0-9]+", "-")
                        .replaceAll("^-|-$", "");

                if (safeName.isBlank()) {

                    safeName = "recipe-" + recipeId;
                }

                return safeName + ".json";
            }
}
