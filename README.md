# AOOP — Project Updates

## Update 1: Entity and Repository
- Created all 15 JPA Entity classes matching the EER diagram.
- Created all 15 JpaRepository classes to handle database CRUD operations.

## Update 2: REST Controllers and DTOs
- Implemented REST API Controllers (`@RestController`) for all 15 entities to handle client requests (GET, POST, PUT, DELETE).
- Created flat Request DTO classes to manage foreign-key associations and relationships cleanly.
- Implemented global exception handling (`@ControllerAdvice` and `ResourceNotFoundException`) to format error responses as structured JSON.
- Enhanced JPA Repository interfaces with custom finder query methods.

---

### EER Diagram
![ER diagram](images/readme.jpg)
