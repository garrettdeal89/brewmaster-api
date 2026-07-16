package com.gdeal.brewmaster.dto;
import java.time.LocalDateTime;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "Standard error response returned by the API."
)
public class ApiError {

    @Schema(
    description = "Time the error occurred"
    )
    private LocalDateTime timestamp;
    
    @Schema(
    description = "HTTP status code",
    example = "400"
    )
    private int status;
    
    @Schema(
    description = "HTTP status description",
    example = "Bad Request"
    )
    private String error;
    
    @Schema(
    description = "Detailed description of the error",
    example = "Request validation failed"
    )
    private String message;

    @Schema(
        defaultValue = "Validation errors associated with individual fields"
    )
    private Map<String, String> fielderrors;


public ApiError () {}

    public ApiError(
        int status,
        String error,
        String message) {

        this(

            status,
            error,
            message,
            null
        );
    }

    public ApiError(

        int status,
        String error,
        String message,
        Map<String, String> fieldErrors) {

            this.timestamp = timestamp;
            this. status = status;
            this.error = error;
            this.message = message;
            this.fielderrors = fieldErrors;
        }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }
    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getFieldErrors() {
        return fielderrors;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fielderrors = fieldErrors;
    }
}
