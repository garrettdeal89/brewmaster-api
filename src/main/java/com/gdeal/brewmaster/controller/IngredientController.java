package com.gdeal.brewmaster.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gdeal.brewmaster.dto.CreateIngredientRequest;
import com.gdeal.brewmaster.dto.IngredientDTO;
import com.gdeal.brewmaster.service.IngredientService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    

    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @GetMapping
    public List<IngredientDTO> getAllIngredients() {

        return ingredientService.getAllIngredients();
    }


    @GetMapping("/{id}")
    public IngredientDTO getIngredientById(
            @PathVariable Long id) {

        return ingredientService.getIngredientById(id);
    }


    @PostMapping
    public ResponseEntity<IngredientDTO> createIngredient(
            @Valid @RequestBody CreateIngredientRequest request) {

        IngredientDTO ingredient =
                ingredientService.createIngredient(request);

        return ResponseEntity
                .created(URI.create("/api/ingredients/" + ingredient.getId()))
                .body(ingredient);
    }


    @PutMapping("/{id}")
    public IngredientDTO updateIngredient(
            @PathVariable Long id,
            @Valid @RequestBody CreateIngredientRequest request) {

        return ingredientService.updateIngredient(id, request);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(
            @PathVariable Long id) {

        ingredientService.deleteIngredient(id);

        return ResponseEntity.noContent().build();
    }
}
