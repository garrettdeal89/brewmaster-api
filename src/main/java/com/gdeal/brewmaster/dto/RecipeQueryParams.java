package com.gdeal.brewmaster.dto;

import com.gdeal.brewmaster.model.CoffeeType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;



public class RecipeQueryParams {
    //Page Field Documentation
    @Schema(
        description = "Page number (starting at 0).", 
        example = "0", 
        minimum = "0")

    @Min(value = 0, message = "Page number must be greater than or equal to 0.")
    private int page = 0;
   
    //Size Field Documentation
    @Schema(
        description = "Number of recipes returned per page.", 
        example = "10",
        minimum = "1",
        maximum = "100"
    )

    @Min(value = 1, message = "Page size must be greater than or equal to 1.")
    @Max(value = 100, message = "Page size must be less than or equal to 100.")
    private int size = 10;

    //Sortfield Documentation
    @Schema(
        description = "Field used to sort the results",
        allowableValues = {"id", "name", "type"},
        example = "id"
    )
   
    private String sortField = "id";
    
    //SortDirection Documentation
    @Schema(
        description = "Sort direction",
        allowableValues = {"asc", "desc"},
        example = "asc"
    )

    @Pattern(regexp = "asc|desc", message = "Sort direction must be either 'asc' or 'desc'.")
    private String sortDirection = "asc";
    
    //Type Documentation
    @Schema(
    description = "Optional coffee type used to filter recipes"
    )
    
    private CoffeeType type;

    public RecipeQueryParams() {}

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }
}
