package com.gdeal.brewmaster.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a coffee brew method.")
public class BrewMethodDTO {

    @Schema(
        description = "Unique identifier",
        example = "1"
    )
    private Long id;

    @Schema(
        description = "Name of the brew method",
        example = "Pressure"
    )
    private String name;

    public BrewMethodDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
