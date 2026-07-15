package com.gdeal.brewmaster.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(
    description = "Public share link information for a recipe."
)
public class RecipeShareDTO {
    
    @Schema(
    description = "Unique token used to access the shared recipe."
)
private UUID token;

@Schema(
    description = "Public path used to retrieve shared recipe."
)
private String sharePath;

@Schema(
    description = "Date and time the share link was created."
)
private LocalDateTime createdAt;


public RecipeShareDTO(

    UUID token,
    String sharePath,
    LocalDateTime createdAt) {

        this.token = token;
        this.sharePath = sharePath;
        this.createdAt = createdAt;
    }

    public UUID getToken() {

        return token;
    }

    public String getSharePath() {

        return sharePath;
    }

    public LocalDateTime getCreatedAt() {

        return createdAt;
    }


}