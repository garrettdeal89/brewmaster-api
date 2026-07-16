package com.gdeal.brewmaster.exception;



public class RecipeExportException extends RuntimeException {
    
    public RecipeExportException(

        Long recipeId,
        Throwable cause) {

            super(
                "Failed to export recip with id: " + recipeId,
                cause
            );
        }
}
