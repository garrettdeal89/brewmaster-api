package com.gdeal.brewmaster.controller;

import com.gdeal.brewmaster.dto.CreateRecipeRequest;
import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.dto.RecipeQueryParams;
import com.gdeal.brewmaster.exception.ResourceNotFoundException;
import com.gdeal.brewmaster.model.CoffeeType;
import com.gdeal.brewmaster.service.RecipeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

@Autowired
private MockMvc mockMvc;

@MockitoBean
private RecipeService recipeService;

@Test
void getAllRecipes_shouldReturnList() throws Exception {

        Page<RecipeDTO> mockRecipes = new PageImpl<>(List.of(
                new RecipeDTO(
                        1L,
                        CoffeeType.LATTE,
                        "Latte",
                        "steamed milk + espresso"
                ),
                new RecipeDTO(
                        2L,
                        CoffeeType.CAPPUCCINO,
                        "Cappuccino",
                        "Equal parts espresso, milk, + foam."
                )
        ));

        when(recipeService.getAllRecipes(any(RecipeQueryParams.class)))
                .thenReturn(mockRecipes);

        mockMvc.perform(get("/api/recipes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message")
                        .value("Recipes retrieved successfully"))
                .andExpect(jsonPath("$.data.content.length()")
                        .value(2))
                .andExpect(jsonPath("$.data.content[0].name")
                        .value("Latte"))
                .andExpect(jsonPath("$.data.content[1].name")
                        .value("Cappuccino"));
        }

        @Test
        void getAllRecipes_shouldReturn400_whenInvalidSortField() throws Exception {

        when(recipeService.getAllRecipes(any(RecipeQueryParams.class)))
                .thenThrow(new IllegalArgumentException("Invalid sort field"));

        mockMvc.perform(get("/api/recipes")
                        .param("sortField", "invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("Invalid sort field"));
        }

        @Test
        void createRecipe_shouldReturn404_whenIngredientNotFound() throws Exception {

        when(recipeService.createRecipe(any()))
                .thenThrow(new ResourceNotFoundException("Ingredient", 999L));

        mockMvc.perform(post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "type": "LATTE",
                                "name": "Invalid Latte",
                                "description": "Testing missing ingredient",
                                "ingredientIds": [999]
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message")
                        .value("Ingredient not found with id: 999"));
        }

        @Test
        void updateRecipe_shouldReturn200_withIngredients() throws Exception {

        RecipeDTO updatedRecipe = new RecipeDTO(
                1L,
                CoffeeType.LATTE,
                "Updated Latte",
                "Espresso with steamed milk.",
                "Pressure",
                Set.of("Espresso", "Milk")
        );

        when(recipeService.updateRecipe(
                eq(1L),
                any(CreateRecipeRequest.class)))
                .thenReturn(updatedRecipe);

        mockMvc.perform(put("/api/recipes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "type": "LATTE",
                                "name": "Updated Latte",
                                "description": "Espresso with steamed milk.",
                                "ingredientIds": [
                                        1,
                                        2
                                ]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message")
                        .value("Recipe updated successfully"))
                .andExpect(jsonPath("$.data.name")
                        .value("Updated Latte"))
                .andExpect(jsonPath("$.data.brewMethod")
                        .value("Pressure"))
                .andExpect(jsonPath("$.data.ingredients",
                        containsInAnyOrder("Espresso", "Milk")));
        }

        @Test
void getAllRecipes_shouldFilterByIngredient() throws Exception {

    Page<RecipeDTO> recipes = new PageImpl<>(List.of(
            new RecipeDTO(
                    1L,
                    CoffeeType.LATTE,
                    "Latte",
                    "Espresso with steamed milk.",
                    "Pressure",
                    Set.of("Espresso", "Milk"))
    ));

    when(recipeService.getAllRecipes(any(RecipeQueryParams.class)))
            .thenReturn(recipes);

    mockMvc.perform(get("/api/recipes")
            .param("ingredient", "Milk"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.content[0].name")
                    .value("Latte"));
}

@Test
void getAllRecipes_shouldFilterByBrewMethod() throws Exception {

    Page<RecipeDTO> recipes = new PageImpl<>(List.of(
            new RecipeDTO(
                    1L,
                    CoffeeType.LATTE,
                    "Espresso",
                    "Classic espresso.",
                    "Pressure",
                    Set.of("Espresso"))
    ));

    when(recipeService.getAllRecipes(any(RecipeQueryParams.class)))
            .thenReturn(recipes);

    mockMvc.perform(get("/api/recipes")
            .param("brewMethod", "Pressure"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.content[0].brewMethod")
                    .value("Pressure"));
}

@Test
void getAllRecipes_shouldFilterByMultipleCriteria() throws Exception {

    Page<RecipeDTO> recipes = new PageImpl<>(List.of(
            new RecipeDTO(
                    1L,
                    CoffeeType.LATTE,
                    "Latte",
                    "Espresso with steamed milk.",
                    "Pressure",
                    Set.of("Espresso", "Milk"))
    ));

    when(recipeService.getAllRecipes(any(RecipeQueryParams.class)))
            .thenReturn(recipes);

    mockMvc.perform(get("/api/recipes")
            .param("type", "LATTE")
            .param("ingredient", "Milk")
            .param("brewMethod", "Pressure"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.content[0].name")
                    .value("Latte"))
            .andExpect(jsonPath("$.data.content[0].brewMethod")
                    .value("Pressure"));
}
}
