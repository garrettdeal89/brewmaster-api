package com.gdeal.brewmaster.controller;

import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.dto.RecipeQueryParams;
import com.gdeal.brewmaster.model.CoffeeType;
import com.gdeal.brewmaster.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecipeService recipeService;

    @Test
    void getAllRecipes_shouldReturnList() throws Exception {

        Page<RecipeDTO> mockRecipes = new PageImpl<>(List.of(
                new RecipeDTO(1L, CoffeeType.LATTE, "Latte", "steamed milk + espresso"),
                new RecipeDTO(2L, CoffeeType.CAPPUCCINO, "Cappuccino", "Equal parts espresso, milk, + foam.")
        ));

        when(recipeService.getAllRecipes(any(RecipeQueryParams.class)))
                .thenReturn(mockRecipes);

        mockMvc.perform(get("/api/recipes"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value(200))
        .andExpect(jsonPath("$.message").value("Recipes retrieved successfully"))
        .andExpect(jsonPath("$.data.content.length()").value(2))
        .andExpect(jsonPath("$.data.content[0].name").value("Latte"))
        .andExpect(jsonPath("$.data.content[1].name").value("Cappuccino"));
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
}
