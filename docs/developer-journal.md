
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
-

### Key Notes
-

## Sprint 6: Delete Recipes

### Features
-

### Key Notes
-

## Sprint 7: Service and Controller Testing

### Tests
-

### Key Notes
-
