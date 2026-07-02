package com.gdeal.brewmaster.dto;

import com.gdeal.brewmaster.model.CoffeeType;



public class RecipeQueryParams {
    private int page = 0;
    private int size = 10;
    private String sortField = "id";
    private String sortDirection = "asc";
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
