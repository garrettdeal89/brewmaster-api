package com.gdeal.brewmaster.service;

import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.model.CoffeeType;
import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    void getRecipeById_shouldReturnRecipe_whenExists() {

        // Arrange
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setType(CoffeeType.LATTE);
        recipe.setName("Latte");
        recipe.setDescription("Milk + espresso");

        when(recipeRepository.findById(1L))
                .thenReturn(Optional.of(recipe));

        // Act
        RecipeDTO result = recipeService.getRecipeById(1L);

        // Assert
        assertEquals("Latte", result.getName());
        assertEquals(CoffeeType.LATTE, result.getType());

        verify(recipeRepository, times(1)).findById(1L);
    }
}

