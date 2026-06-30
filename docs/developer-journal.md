
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