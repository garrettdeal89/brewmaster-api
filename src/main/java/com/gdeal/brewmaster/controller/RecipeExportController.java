package com.gdeal.brewmaster.controller;

import com.gdeal.brewmaster.dto.RecipeExportResult;
import com.gdeal.brewmaster.service.RecipeExportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("api/recipes")
@Tag(
    name = "Recipe Export",
    description = "Endpoints for exporting coffee recipes."
)
public class RecipeExportController {
    
    private final RecipeExportService recipeExportService;

    public RecipeExportController(RecipeExportService recipeExportService) {

        this.recipeExportService = recipeExportService;
    }

    @Operation(summary = "Export a recipe as a JSON file")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Recipe exported successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Recipe not found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Recipe export failed"
            )
    })

    @GetMapping(

        value = "/{recipeId}/export",
        produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<byte[]> exportRecipe(

        @PathVariable
        Long recipeId) {

            RecipeExportResult export = recipeExportService.exportRecipeAsJson(recipeId);

            ContentDisposition disposition = ContentDisposition
                .attachment()
                .filename(

                    export.getFilename(),
                    StandardCharsets.UTF_8
                )
                .build();

            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(

                    HttpHeaders.CONTENT_DISPOSITION,
                    disposition.toString()
                )

                .contentLength(export.getContent().length)
                .body(export.getContent());
        }
}
