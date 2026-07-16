package com.gdeal.brewmaster.controller;

import com.gdeal.brewmaster.config.SecurityConfig;
import com.gdeal.brewmaster.dto.RecipeExportResult;
import com.gdeal.brewmaster.security.RestAccessDeniedHandler;
import com.gdeal.brewmaster.security.RestAuthenticationEntryPoint;
import com.gdeal.brewmaster.service.RecipeExportService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeExportController.class)
@Import({
        SecurityConfig.class,
        RestAuthenticationEntryPoint.class,
        RestAccessDeniedHandler.class
})
class RecipeExportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecipeExportService recipeExportService;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    @Test
    void exportRecipe_shouldReturnJsonDownload() throws Exception {

        String json = """
                {
                  "id": 1,
                  "type": "LATTE",
                  "name": "Latte",
                  "description": "Espresso with steamed milk.",
                  "brewMethod": "Pressure",
                  "ingredients": [
                    "Espresso",
                    "Milk"
                  ]
                }
                """;

        byte[] content =
                json.getBytes(StandardCharsets.UTF_8);

        RecipeExportResult exportResult =
                new RecipeExportResult(
                        "latte.json",
                        content
                );

        when(recipeExportService.exportRecipeAsJson(1L))
                .thenReturn(exportResult);

        mockMvc.perform(get("/api/recipes/1/export"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON))
                .andExpect(header().string(
                        "Content-Disposition",
                        containsString(
                                "filename*=UTF-8''latte.json"
                        )
                ))
                .andExpect(header().longValue(
                        "Content-Length",
                        content.length
                ))
                .andExpect(content().bytes(content));
    }
}