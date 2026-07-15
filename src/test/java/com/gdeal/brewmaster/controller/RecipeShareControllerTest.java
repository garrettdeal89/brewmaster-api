package com.gdeal.brewmaster.controller;

import com.gdeal.brewmaster.config.SecurityConfig;
import com.gdeal.brewmaster.dto.RecipeDTO;
import com.gdeal.brewmaster.dto.RecipeShareDTO;
import com.gdeal.brewmaster.model.CoffeeType;
import com.gdeal.brewmaster.service.RecipeShareService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request
        .SecurityMockMvcRequestPostProcessors.jwt;

import static org.springframework.test.web.servlet.request
        .MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request
        .MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.status;

@WebMvcTest(RecipeShareController.class)
@Import(SecurityConfig.class)
class RecipeShareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecipeShareService recipeShareService;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    @Test
    void createShareLink_shouldReturn201_withJwt()
            throws Exception {

        UUID token = UUID.fromString(
                "f4f89f07-1e44-4f87-abbc-960a7fa980a9"
        );

        LocalDateTime createdAt =
                LocalDateTime.of(2026, 7, 15, 17, 8, 55);

        RecipeShareDTO shareDTO = new RecipeShareDTO(
                token,
                "/api/shared-recipes/" + token,
                createdAt
        );

        when(recipeShareService.createShareLink(1L))
                .thenReturn(shareDTO);

        mockMvc.perform(post("/api/recipes/1/share")
                        .with(jwt().jwt(jwt ->
                                jwt.claim("role", "USER")
                        )))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON))
                .andExpect(header().string(
                        "Location",
                        "/api/shared-recipes/" + token
                ))
                .andExpect(jsonPath("$.status")
                        .value(201))
                .andExpect(jsonPath("$.message")
                        .value(
                                "Recipe share link created successfully"
                        ))
                .andExpect(jsonPath("$.data.token")
                        .value(token.toString()))
                .andExpect(jsonPath("$.data.sharePath")
                        .value(
                                "/api/shared-recipes/" + token
                        ))
                .andExpect(jsonPath("$.data.createdAt")
                        .exists());
    }

    @Test
    void createShareLink_shouldReturn401_withoutJwt()
            throws Exception {

        mockMvc.perform(post("/api/recipes/1/share"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getSharedRecipe_shouldReturn200_withoutJwt()
            throws Exception {

        UUID token = UUID.fromString(
                "f4f89f07-1e44-4f87-abbc-960a7fa980a9"
        );

        RecipeDTO sharedRecipe = new RecipeDTO(
                1L,
                CoffeeType.AMERICANO,
                "Americano",
                "Espresso with hot water.",
                "Pressure",
                Set.of("Espresso", "Hot Water")
        );

        when(recipeShareService.getSharedRecipe(token))
                .thenReturn(sharedRecipe);

        mockMvc.perform(get(
                        "/api/shared-recipes/{token}",
                        token
                ))
                .andExpect(status().isOk())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status")
                        .value(200))
                .andExpect(jsonPath("$.message")
                        .value(
                                "Shared recipe retrieved successfully"
                        ))
                .andExpect(jsonPath("$.data.id")
                        .value(1))
                .andExpect(jsonPath("$.data.type")
                        .value("AMERICANO"))
                .andExpect(jsonPath("$.data.name")
                        .value("Americano"))
                .andExpect(jsonPath("$.data.brewMethod")
                        .value("Pressure"))
                .andExpect(jsonPath("$.data.ingredients.length()")
                        .value(2));
    }
}
