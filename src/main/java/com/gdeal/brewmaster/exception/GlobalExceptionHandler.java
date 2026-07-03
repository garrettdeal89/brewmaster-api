package com.gdeal.brewmaster.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.gdeal.brewmaster.dto.ApiError;

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
}
