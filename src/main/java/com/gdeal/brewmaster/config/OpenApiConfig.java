package com.gdeal.brewmaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI brewmasterOpenAPI() {

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
        .email("garrettdeal89@gmail.com")
        )
    );

    }
}
