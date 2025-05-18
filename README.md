# Library API

## Overview
This is a Spring Boot RESTful API for managing a simple library system. It supports registering borrowers and books, borrowing/returning books, and listing books, borrowers, lendings, and available/borrowed books.

## Features
- Register new borrowers and books
- List all books and borrowers
- Borrow and return books
- List all lendings
- List all currently borrowed books
- List all available books
- Data validation and error handling
- Environment configuration (see below)
- Docker support

## API Endpoints
- `POST /borrowers` - Register a new borrower
- `GET /borrowers` - List all borrowers
- `POST /books` - Register a new book
- `GET /books` - List all books
- `POST /borrowers/{borrowerId}/borrow/{bookId}` - Borrow a book
- `POST /borrowers/{borrowerId}/return/{bookId}` - Return a book
- `GET /borrowers/{borrowerId}/borrowed` - List books currently borrowed by a borrower
- `GET /lendings` - List all lendings
- `GET /lendings/available-books` - List all available books

## Data Model Rules
- Borrower email must be unique
- Books with the same ISBN must have the same title and author
- Multiple copies of a book (same ISBN) are allowed, each with a unique ID
- Only one borrower can borrow a specific book (by ID) at a time

## Error Handling
- Returns user-friendly error messages for duplicate emails, ISBN/title/author mismatches, and borrowing conflicts

## Environment Configuration
- Default config: `src/main/resources/application.yml`
- To support multiple environments, create `application-dev.yml`, `application-prod.yml`, etc. and activate with `spring.profiles.active`.

## Database Justification
- **PostgreSQL** is used for its reliability, ACID compliance, and suitability for transactional systems like a library.

## Assumptions
- See `/assumptions` endpoint or `ASSUMPTIONS.md` for details.

## Running with Docker
```
docker-compose up --build
```

## Running Locally
```
mvn clean install
java -jar target/library-api-0.0.1-SNAPSHOT.jar
```

## API Documentation
- Swagger/OpenAPI will be available at `/swagger-ui.html` (if enabled)

## Unit Tests
- See `src/test/java` for test scaffolding (to be added)
