package com.gdeal.brewmaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI brewmasterOpenAPI() {

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()

            .info(new Info()
                .title("Brewmaster API")
                .version("1.0.0")
                .description("""
                                REST API for managing coffee recipes.

                                Features include:
                                • CRUD operations
                                • Pagination
                                • Sorting
                                • Filtering
                                • Request validation
                                • Standardized API responses
                                """)
                .contact(new Contact()
                    .name("Garrett Deal")
                    .url("https://github.com/garrettdeal89")
                    .email("garrettdeal89@gmail.com")))

            
            .addSecurityItem(new SecurityRequirement()

                .addList(securitySchemeName))
            
                .components(new Components()
                            
                .addSecuritySchemes(

                    securitySchemeName,
                    new SecurityScheme()
                    .name(securitySchemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                )
            );
    }
}
