package com.gdeal.brewmaster.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
public ResponseEntity<Map<String, Object>> handleRecipeNotFound(
        RecipeNotFoundException ex) {

    return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
}

@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<Map<String, Object>> handleIllegalArgument(
        IllegalArgumentException ex) {

    return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
}

    private ResponseEntity<Map<String, Object>> buildErrorResponse(
        HttpStatus status,
        String message) {

    Map<String, Object> error = new HashMap<>();
    error.put("timestamp", LocalDateTime.now());
    error.put("status", status.value());
    error.put("error", status.getReasonPhrase());
    error.put("message", message);

    return new ResponseEntity<>(error, status);
    }
}
