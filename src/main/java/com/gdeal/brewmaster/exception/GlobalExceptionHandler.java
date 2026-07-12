package com.gdeal.brewmaster.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;


import com.gdeal.brewmaster.dto.ApiError;
import com.gdeal.brewmaster.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
public ResponseEntity<ApiError> handleRecipeNotFound(
        RecipeNotFoundException ex) {

        ApiError error = new ApiError(
            404,
            "Not Found",
            ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(error);
}

    @ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<ApiError> handleIllegalArgument(
        IllegalArgumentException ex) {

    ApiError error = new ApiError(
            400,
            "Bad Request",
            ex.getMessage());

    return ResponseEntity.badRequest()
            .body(error);
}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(
        MethodArgumentNotValidException ex) {

    String message = ex.getBindingResult()
            .getFieldError()
            .getDefaultMessage();

    ApiError error = new ApiError(
            400,
            "Bad Request",
            message);

    return ResponseEntity.badRequest()
            .body(error);
    }

   @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(
        ResourceNotFoundException ex) {

    ApiResponse<Void> response = new ApiResponse<>(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            null
    );

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(response);
        }

        @ExceptionHandler(ResourceAlreadyExistsException.class)
        public ResponseEntity<ApiResponse<Void>> handleResourceAlreadyExists(

                ResourceAlreadyExistsException ex) {

                        ApiResponse<Void> response = new ApiResponse<>(
                                HttpStatus.CONFLICT.value(),
                                ex.getMessage(),
                                null
                        );

                        return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
                }
}
