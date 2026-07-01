package com.gdeal.brewmaster.controller;

import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.model.CoffeeType;
import com.gdeal.brewmaster.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest
public class RecipeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecipeService recipeService;

    @Test
    void getAllRecipes_shouldReturnList() throws Exception {

        List<RecipeDTO> mockRecipes = List.of(
            new RecipeDTO(1L, CoffeeType.LATTE, "Latte", "steamed milk + espresso"),
            new RecipeDTO(2L, CoffeeType.CAPPUCCINO, "Cappuccino", "Equal parts espresso, milk, + foam.")
        );

        when(recipeService.getAllRecipes()).thenReturn(mockRecipes);

        mockMvc.perform(get("/api/recipes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Latte"))
                .andExpect(jsonPath("$[1].name").value("Cappuccino"));
    }
}
