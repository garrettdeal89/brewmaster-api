package com.gdeal.brewmaster.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "Paginated API response metadata and content."
)
public class PageResponse<T> {
    
    @Schema(
        description = "Items returned on the current page."
    )
    private List<T> content;

    @Schema(
        description = "Current zero-based page number.",
        example = "0"
    )
    private int page;
    
    @Schema(
        description = "Maximum number of items per page.",
        example = "10"
    )
    private int size;

    @Schema(
        description = "Total number of matching items.", 
        example = "25"
    )
    private long totalElements;

    @Schema(
        description = "Total number of available pages.",
        example = "3"
    )
    private int totalPages;

    @Schema(
        description = "if this page is the first.", 
        example = "true"
    )
    private boolean first;

     @Schema(
        description = "if this page is the last.",
        example = "false"
    )
    private boolean last;

    @Schema(
        description = "If the current page contains no items."
    )
    private boolean empty;

    public PageResponse(

        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        boolean empty) {

            this.content = content;
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.first = first;
            this.last = last;
            this.empty = empty;
        }

        public List<T> getContent() {

            return content;
        }

        public int getPage() {

            return page;
        }

        public int getSize() {

            return size;
        }

        public long getTotalElements() {

            return totalElements;
        }

        public int getTotalPages() {

            return totalPages;
        }

        public boolean getFirst() {

            return first;
        }

        public boolean getLast() {

            return last;
        }

        public boolean getEmpty() {

            return empty;
        }
}
