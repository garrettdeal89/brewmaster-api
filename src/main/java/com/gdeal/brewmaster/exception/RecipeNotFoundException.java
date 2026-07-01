package com.gdeal.brewmaster.exception;

public class RecipeNotFoundException extends RuntimeException {
    
    public RecipeNotFoundException(Long id) {
        
        super("Recipe Not Found: " + id);
    }
}
