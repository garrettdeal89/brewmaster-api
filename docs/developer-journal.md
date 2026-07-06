
# Developer Journal 

## Sprint 0: Project Initiation 
### Feats:
- initialized project root  

### Features
-project initiation

## Sprint 1: Spring Boot Bootstrap
### Features
- generated 3.5 soring boot project using spring initializr
- configured project with Maven and Java 21
- added dependencies: Spring Web, Validation, Spring Data JPA, H2 Database, Lombok, DevTools, OpenAPI, and Actuator
- executed maven clean compile: build success. 

### Key Notes
- pom.xml manages dependencies and build config. 
- maven expects standard directory layout 
- spring boot used to provide defaults to reduce boilerplate.  

## Sprint 2: Health Response Implementation
### Features
- implemented HealthResponse controller and DTO
- created health response endpoint.
- returns JSON serialization via Jackson

### Key Notes
- addresses separation concerns via DTO layer
- scalable API design

## Sprint 3: Implement Recipe Controller, Service Layer and Database
### Features:
- implemented sercive later for coffee recipes
-  implemented recipe controller endpoint
- implemented recipe repositry and interface
- implemented database seeder

### Key Notes
- developed fully persistent REST API
- h2 DB URL: jdbc:h2:mem:brewmasterdb

### Fix: Environment configuration mismatch causing database resolution failure

## Sprint 4: Recipe Creation API

### Features
- Added CreateRecipeRequest DTO for API input.
- Implemented POST /api/recipes endpoint.
- Added Bean Validation using @Valid, @NotBlank, and @NotNull.
- Persisted recipes using Spring Data JPA.
- Updated endpoint to return HTTP 201 Created with a Location header.
- Verified functionality using Swagger UI.

### Key Notes
- Request DTOs should be separate from response DTOs and JPA entities.
- Bean Validation integrates seamlessly with Spring using @Valid.
- Returning proper HTTP status codes (201 Created) makes an API more RESTful and standards-compliant.

## Sprint 5: Update Recipes

### Features
- Implemented PUT /api/recipes/{id} endpoint to update existing recipes.
- Added update functionality to the RecipeService.
- Reused CreateRecipeRequest DTO for both create and update operations.
- Returned updated RecipeDTO after a successful update.
- Refactored duplicate entity mapping logic into a shared helper method (updateRecipeFromRequest).

### Key Notes
-Practiced RESTful update semantics using HTTP PUT.
- Reinforced separation of concerns by keeping business logic in the service layer.
- Eliminated duplicated code through method extraction.
- refactors improve maintainability without changing behavior

## Sprint 6: Delete Recipes

### Features
- Implemented DELETE /api/recipes/{id} endpoint.
- Added delete functionality to the RecipeService.
- Reused RecipeNotFoundException for consistent error handling.
- Returned 204 No Content on successful deletion.

### Key Notes
- Completed full CRUD functionality for the Recipe resource.
- REST conventions for DELETE requests.
= Used ResponseEntity.noContent() to return the correct HTTP response.
- Verified deletion behavior using Swagger.

## Sprint 7: Service and Controller Testing

### Tests
- Added unit tests for the RecipeService using JUnit 5 and Mockito.
- Mocked the RecipeRepository to isolate service logic.
- Added MockMvc controller tests for Recipe endpoints.
- Verified HTTP status codes, JSON responses, and controller behavior.
- Updated tests to use @MockitoBean instead of the deprecated @MockBean (Spring Boot 3.5).

### Key Notes
- AAA (Arrange, Act, Assert) testing pattern.
- Practiced mocking dependencies with Mockito.
- Tested the service layer independently from the web layer.
- MockMvc simulates HTTP requests without starting the application.
- Established an automated regression test suite using mvn test.

## Sprint 8: Pagination Support

### Feature
- Implemented pageable GET /api/recipes endpoint using Spring Data Pageable
- Added page and size query parameters with default values
- Integrated Page<RecipeDTO> response structure in controller and service

### Key Notes
- Established foundational REST pagination pattern using Spring Data JPA
- Enabled scalable recipe retrieval for large datasets
- Ensured DTO-based responses remained consistent with API design

## Sprint 9: Sorting Support

### Feature
- Added dynamic sorting to /api/recipes via sortField and sortDirection
- Integrated Spring Data Sort with pageable queries
- Implemented validation whitelist for allowed sort fields (id, name, type)

### Key Notes
- Prevented invalid sort inputs using defensive validation (returns 400 Bad Request)
- Improved API flexibility while maintaining safe query execution
- Refactored exception handling for clean error responses via GlobalExceptionHandler

## Sprint 10: Filtering Support

### Feature
- Added filtering by CoffeeType via type query parameter
- Implemented repository method findByType(CoffeeType, Pageable)
- Updated service layer to conditionally apply filtering logic
- Combined filtering with pagination and sorting in a single endpoint

### Key Notes
- nabled dynamic query composition (filter + sort + pagination)
- Maintained single REST endpoint instead of multiple specialized endpoints
- Improved API usability and real-world parity with production REST services
- Strengthened separation of concerns between controller, service, and repository layers

### Refactor Query Params
- Refactored the recipe listing endpoint to use the Query Object pattern by introducing the 
- RecipeQueryParams DTO.
- Consolidated pagination, sorting, and filtering parameters into a single object to simplify the   controller and improve maintainability.
- Updated the service layer to consume the new query object.
- Refactored controller tests to accommodate the new endpoint signature.

### Key Notes
-educed controller method complexity by eliminating multiple individual request parameters.
- Improved extensibility for future query options.
- Demonstrated a common enterprise design pattern used in Spring Boot applications.
- Updated and verified all unit and controller tests.

# Sprint 11: Standardized API Responses

### Feature
- Introduced the generic ApiResponse<T> wrapper for successful API responses.
- Updated recipe endpoints to return a consistent response structure.

### Key Notes
- Standardized successful responses across the API.
- Improved consistency for API consumers.
- Established a foundation for future metadata such as paging information and request identifiers.

# Sprint 12: Validation

### Feature
- Added Bean Validation to RecipeQueryParams.
- Implemented validation for pagination and sorting parameters.
- Added validation exception handling to the global exception handler.

### Key Notes
- Prevented invalid page numbers and page sizes.
- Restricted sort direction to supported values.
- Returned consistent HTTP 400 responses for invalid requests.
- Improved API robustness by validating requests before reaching the service layer.

# Sprint 13: Standardized Error Response

### Feature
- Introduced the ApiError DTO to replace dynamically constructed error maps.
- Updated the global exception handler to return strongly typed error responses.

### Key Notes
- Standardized all error responses.
- Improved type safety and maintainability.
- Preserved a consistent JSON error format across the API.
- Simplified exception handling logic.

# Sprint 14: OpenAPI Documentation

### Feature
- Configured OpenAPI metadata for the Brewmaster API.
- Added controller tags for Health and Recipe endpoints.
- Documented endpoints, response codes, request models, query parameters, and DTO schemas.
- Enhanced generated Swagger documentation with descriptions and examples.

### Key Notes
- rganized API documentation into logical sections.
- Improved discoverability of API functionality.
- Added schema metadata for request and response models.

