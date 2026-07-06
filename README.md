
# BrewMaster API

A RESTful API for managing specialty coffee recipes built with Java and Spring Boot.

## Purpose

This project demonstrates professional backend development practices using modern Java technologies.

The API will allow users to create, retrieve, update, and delete coffee recipes while following clean architecture principles.

## Tech Stack

- Java 23
- Spring Boot
- Maven
- Spring Data JPA
- H2 Database
- JUnit 5
- Mockito
- Swagger / OpenAPI
- Git & GitHub

## Features
- RESTful CRUD API
- Layered Architecture (Controller → Service → Repository)
- Spring Data JPA
- H2 Database
- DTO-based API design
- Bean Validation
- Global Exception Handling
- Standardized API Responses
- Pagination
- Sorting
- Dynamic Filtering
- Query Object Pattern
- Dynamic Search using JPA Specifications
- OpenAPI / Swagger Documentation
- Unit and Controller Tests

## Technologies
- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Spring Validation
- Springdoc OpenAPI (Swagger)
- JUnit 5
- Mockito
- MockMvc

## Project Architecture
- Client
   │
   ▼
- Controllers
   │
   ▼
- Services
   │
   ▼
- Repositories
   │
   ▼
- Database

## Additional supporting packages:
- DTOs
- Specifications
- Exception Handling
- Validation
- API Features
- Recipe Management
- Create Recipe
- Retrieve All Recipes
- Retrieve Recipe by ID
- Update Recipe
- Delete Recipe
- Pagination
- GET /api/recipes?page=0&size=10
- Sorting
- GET /api/recipes?sortField=name&sortDirection=asc
- Filtering
- GET /api/recipes?type=LATTE
- Searching
- GET /api/recipes?name=latte

## Supports:
- partial matching
- case-insensitive search
- combined search and filtering

### Example:

- GET /api/recipes?name=latte&type=LATTE&page=0&sortField=name

## Validation
- Incoming requests are validated using Bean Validation.

### Examples:
- Invalid page numbers
- Invalid page sizes
- Invalid sort fields
- Invalid request bodies

- All validation errors return standardized JSON responses.

## API Documentation
- Interactive documentation is available through Swagger UI.

- http://localhost:8080/swagger-ui.html

### The documentation includes:

- endpoint descriptions
- request examples
- response examples
- query parameter documentation
- schema documentation
- Testing

## Current automated tests include:
- Service Layer Tests
- Controller Tests
- MockMvc Integration Tests
- Exception Handling Tests
- Running the Project

### Clone the repository:
- git clone https://github.com/garrettdeal89/brewmaster-api.git

### Run:
- mvn spring-boot:run

### Swagger UI:
- http://localhost:8080/swagger-ui.html

### H2 Console:
- http://localhost:8080/h2-console
Current Project Status

## Completed:
-Spring Boot project setup
-CRUD API
- DTO architecture
- Repository pattern
- Service layer
- Global exception handling
- Standard API responses
- Pagination
- Sorting
- Filtering
- Query Object pattern
- Bean Validation
- Swagger / OpenAPI
- Dynamic search using JPA Specifications
- Unit testing
- Future Roadmap
- Ingredient Management
- Brewing Methods
- Recipe Instructions
- Authentication (JWT)
- User Accounts
- Favorite Recipes
- PostgreSQL Support
- Docker
- GitHub Actions CI/CD
- Purpose

### This project is intended to demonstrate practical backend engineering skills including:

- REST API design
- Spring Boot development
- JPA and Hibernate
- Layered application architecture
- Validation
- Testing
- API documentation
- Clean, maintainable code
- Enterprise design patterns