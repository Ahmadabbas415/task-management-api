# Task Management REST API (Spring Boot)

A robust and production-ready Task Management REST API built using Spring Boot, featuring clean architecture and fully covered with Unit Tests (JUnit 5 & Mockito).

This project was entirely designed, developed, and tested by me as part of my backend engineering portfolio.

---

## Tech Stack & Dependencies

* Backend: Java 26, Spring Boot 
* Data Access: Spring Data JPA
* Database: H2 In-Memory Database (Fast & Zero-Configuration)
* Testing: JUnit 5, Mockito
* Utilities: Lombok (For boilerplate reduction)

---

## Architecture Design
The project follows a standard layered architecture to ensure separation of concerns and maintainability:
1. Controller Layer: Exposes REST endpoints and handles HTTP Requests/Responses.
2. Service Layer: Contains business logic, input validation, and exception handling.
3. Repository Layer: Interacts with the database via Spring Data JPA.
4. Entity Layer: Defines the database schema and data models.

---

## API Endpoints

* POST /api/tasks : Create a new task (Payload JSON: {"title": "Task Name", "completed": false})
* PUT /api/tasks/{id} : Update an existing task (Payload JSON: {"title": "Updated Name", "completed": true})
* DELETE /api/tasks/{id} : Delete a task by ID

---

## Testing & Code Quality
This project places a heavy emphasis on reliability. The Service Layer is thoroughly tested using JUnit 5 and Mockito to mock repository behavior:
* Behavior Verification (Mockito.verify): Ensures methods are invoked correctly.
* Exception Testing (assertThrows): Validates constraint enforcement (e.g., rejecting empty titles).

To run the test suite locally:
mvn test

---

## How to Run Locally

1. Open the project in IntelliJ IDEA (or any Java IDE).
2. Run the application via TaskApplication.java or using the terminal: mvn spring-boot:run
3. Open Postman and start testing the endpoints at http://localhost:8080/api/tasks.
