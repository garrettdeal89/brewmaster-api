package com.gdeal.brewmaster.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

        Map<String, String> fieldErrors =
                new LinkedHashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        fieldErrors.putIfAbsent(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()
                        )
                );

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "Request validation failed",
                fieldErrors
        );

        return ResponseEntity
                .badRequest()
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

        @ExceptionHandler(InvalidCredentialsException.class)
        public ResponseEntity<ApiResponse<Void>> handleInvalidCredentials (

                InvalidCredentialsException ex) {

                        ApiResponse<Void> response = new ApiResponse<>(

                                HttpStatus.UNAUTHORIZED.value(),
                                ex.getMessage(),
                                null
                        );

                        return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(response);
                }
        
        @ExceptionHandler(RecipeExportException.class)
        public ResponseEntity<ApiResponse<Void>> handleRecipeExportException(

                RecipeExportException ex) {

                        ApiResponse<Void> response = new ApiResponse<>(

                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                ex.getMessage(),
                                null
                        );

                        return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(response);
                }
        
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ApiError> handleUnreadableMessage(
                HttpMessageNotReadableException ex) {

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Request body contains malformed or invalid JSON."
        );

        return ResponseEntity
                .badRequest()
                .body(error);
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ApiError> handleTypeMismatch(
                MethodArgumentTypeMismatchException ex) {

        String message;

        if (ex.getRequiredType() != null
                && ex.getRequiredType().equals(java.util.UUID.class)) {

                message = "Invalid UUID value for parameter: "
                        + ex.getName();

        } else {
                message = "Invalid value for parameter: "
                        + ex.getName();
        }

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                message
        );

        return ResponseEntity
                .badRequest()
                .body(error);
        }
}
