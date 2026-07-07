package com.gdeal.brewmaster.service;

import org.springframework.stereotype.Service;
import com.gdeal.brewmaster.repository.IngredientRepository;
import com.gdeal.brewmaster.model.Ingredient;
import java.util.List;

import com.gdeal.brewmaster.dto.CreateIngredientRequest;
import com.gdeal.brewmaster.dto.IngredientDTO;




@Service
public class IngredientService {
    
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepository.findAll()
        .stream()
        .map(this::toDTO)
        .toList();
    }

    public IngredientDTO getIngredientById(Long id) {

        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + id));

        return toDTO(ingredient);
    }

     public IngredientDTO createIngredient(CreateIngredientRequest request) {

        Ingredient ingredient = new Ingredient();

        ingredient.setName(request.getName());

        Ingredient savedIngredient =
                ingredientRepository.save(ingredient);

        return toDTO(savedIngredient);
    }


    public IngredientDTO updateIngredient(
            Long id,
            CreateIngredientRequest request) {

        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ingredient not found: " + id));

        ingredient.setName(request.getName());

        Ingredient updatedIngredient =
                ingredientRepository.save(ingredient);

        return toDTO(updatedIngredient);
    }


    public void deleteIngredient(Long id) {

        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ingredient not found: " + id));

        ingredientRepository.delete(ingredient);
    }


    private IngredientDTO toDTO(Ingredient ingredient) {

        return new IngredientDTO(
                ingredient.getId(),
                ingredient.getName()
        );
    }
}

