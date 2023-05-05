# Bookstore Application

This is a Spring Boot application for a bookstore that allows users to filter and view books based on various criteria.

## Getting Started

### Prerequisites

- Java 11 or later
- Maven

### Installing

1. Clone the repository

```sh
git clone https://github.com/<your-github-username>/bookstore.git
cd bookstore
```

2. Build the project

```sh
mvn clean install
```

3. Run the application

```sh
mvn spring-boot:run
```

The application will start at http://localhost:8081.

## API Documentation

### `POST /api/v1/new-book`

Add a new book with fields name,author,price,description.

### `PUT /api/v1/id`

Enter the fields to updateby passing id as pathvariable

### `GET /api/v1/all-books`

Returns all books in the database.

### `GET /api/{id}/book`

Returns the book with the specified ID.

### `GET /api/v1?name="xyz&author="xyz"

Returns all books that match the given filter criteria. The following query parameters are supported:

- `author`: filters books by author
- `name`: filters books by name

## Built With

- Spring Boot
- Spring Data JPA
- MySql Database
- ModelMapper
- JUnit 5

