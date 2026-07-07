package com.gdeal.brewmaster.service;

import com.gdeal.brewmaster.dto.CreateRecipeRequest;
import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.dto.RecipeQueryParams;
import com.gdeal.brewmaster.exception.RecipeNotFoundException;
import com.gdeal.brewmaster.model.Recipe;
import com.gdeal.brewmaster.repository.IngredientRepository;
import com.gdeal.brewmaster.repository.RecipeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import com.gdeal.brewmaster.specification.RecipeSpecifications;
import com.gdeal.brewmaster.model.Ingredient;


@Service
public class RecipeService {

   private final RecipeRepository recipeRepository;
   private final IngredientRepository ingredientRepository;

   private static final Set<String> ALLOWED_SORT_FIELDS =
        Set.of("id", "name", "type");

    public RecipeService(
        RecipeRepository recipeRepository,  
        IngredientRepository ingredientRepository) { 
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = null;
    }

    public Page<RecipeDTO> getAllRecipes(RecipeQueryParams params) {

    if (!ALLOWED_SORT_FIELDS.contains(params.getSortField())) {
        throw new IllegalArgumentException(
                "Invalid sort field: " + params.getSortField()
                + ". Allowed values are: " + ALLOWED_SORT_FIELDS);
    }

    Sort sort = params.getSortDirection().equalsIgnoreCase("desc")
            ? Sort.by(params.getSortField()).descending()
            : Sort.by(params.getSortField()).ascending();

    Pageable pageable = PageRequest.of(
            params.getPage(),
            params.getSize(),
            sort);

    // Build the specification based on the provided query parameters
    Specification<Recipe> spec = Specification.where(null);

    if (params.getName() != null && !params.getName().isBlank()) {
        spec = spec.and(
                RecipeSpecifications.nameContains(params.getName()));
    }

    if (params.getType() != null) {
        spec = spec.and(
                RecipeSpecifications.hasType(params.getType()));
    }

    // Fetch the recipes based on the specification and pagination
    Page<Recipe> recipes = recipeRepository.findAll(spec, pageable);

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

    if (request.getIngredientIds() != null) {

        Set<Ingredient> ingredients = new HashSet<>();

        for (Long ingredientId : request.getIngredientIds()) {

            Ingredient ingredient = ingredientRepository.findById(ingredientId)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Ingredient not found: " + ingredientId));

            ingredients.add(ingredient);
        }

        recipe.setIngredients(ingredients);
    }
}

    private RecipeDTO toDTO(Recipe recipe) {
        return new RecipeDTO(
        recipe.getId(),
        recipe.getType(),
        recipe.getName(),
        recipe.getDescription(),
        recipe.getIngredients()
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toSet())
);
    }
}
