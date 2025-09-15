# Library Management System

## Overview
The **Library Management System** is a Spring Boot application designed to manage books, authors, publishers, and categories in a library. It provides RESTful APIs to perform CRUD operations on books, leveraging Spring Data JPA for database interactions, MapStruct for object mapping, Flyway for database migrations, and Swagger for API documentation. The application uses MySQL as the database and includes features like book details management, author associations, and category assignments.

## Technologies Used
- **Java**: 23
- **Spring Boot**: 3.5.5
- **Spring Data JPA**: For database operations
- **MapStruct**: 1.5.5.Final for object mapping between entities and DTOs
- **Flyway**: For database migrations
- **MySQL**: 8.0 as the database
- **Lombok**: For reducing boilerplate code
- **Swagger/OpenAPI**: For API documentation
- **Maven**: For dependency management and build

## Prerequisites
- **Java 23**: Ensure Java 23 is installed (`java -version` to verify).
- **Maven**: Install Maven for building the project (`mvn -version` to verify).
- **MySQL**: Install MySQL 8.0 and create a database named `libraryManagementSystem`.
- **IDE**: IntelliJ IDEA (recommended) with the Lombok plugin enabled and annotation processing turned on.

## Setup Instructions

### 1. Clone the Repository
Clone the project from GitHub:

```bash
git clone https://github.com/Code81Task/books.git
cd books
```

### 2. Run docker 
Ensure MySQL is running and create a database named `libraryManagementSystem`:
 run this in local project terminal 
```sql
 docker compose up -d
```

Update the `src/main/resources/application.properties` file with your MySQL credentials:
form docker compose file
```properties
spring.datasource.url=jdbc:mysql://localhost:3309/libraryManagementSystem
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
```

### 3. Build the Project
Use Maven to build the project and generate MapStruct mappings:

```bash
mvn clean install
```

### 4. Run the Application
Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

### 5. Access Swagger UI
Explore and test the APIs using Swagger UI at:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)


## Project Structure
- **Entities**: `Book`, `Author`, `Publisher`, `Category` (located in `com.Code81.books.entity`)
    - `Book`: Represents a book with fields like title, publisher, authors, categories, ISBN, etc.
    - Relationships: `@ManyToOne` with `Publisher`, `@ManyToMany` with `Author` and `Category`.
- **DTOs**: Data Transfer Objects for API responses/requests (`com.Code81.books.dto`)
- **Mappers**: MapStruct mappers for entity-DTO conversions (`com.Code81.books.mapper`)
- **Controllers**: REST controllers for handling API requests
- **Services**: Business logic for managing books, authors, etc.
- **Repositories**: Spring Data JPA repositories for database operations

### Book Controller
- **GET /api/v1/books/{id}**: Retrieve a book by ID.
- **PUT /api/v1/books/{id}**: Update a book by ID.
- **DELETE /api/v1/books/{id}**: Delete a book by ID.
- **GET /api/v1/books**: Retrieve all books.
- **POST /api/v1/books**: Create a new book.
- **GET /api/v1/books/category/{categoryId}**: Retrieve books by category ID.

### Publisher Controller
- **GET /api/v1/publishers**: Retrieve all publishers.
- **POST /api/v1/publishers**: Create a new publisher.

### Category Controller
- **GET /api/v1/categories**: Retrieve all categories.
- **POST /api/v1/categories**: Create a new category.

### Category Controller
- **GET /api/v1/authors**: Retrieve all authors.
- **POST /api/v1/authors**: Create a new author.

## Database Migrations
Flyway is used for database migrations. Migration scripts are located in `src/main/resources/db/migration`. On startup, Flyway applies migrations to the `libraryManagementSystem` schema.

## Troubleshooting
- **MapStruct Error**: If you encounter `No qualifying bean of type 'com.Code81.books.mapper.BookMapper'`, ensure:
    - The project is built with `mvn clean install` to generate MapStruct implementations.
    - All mappers (`BookMapper`, `AuthorMapper`, `PublisherMapper`, `CategoryMapper`) are in `com.Code81.books.mapper`.
    - Check `target/generated-sources/annotations` for `BookMapperImpl.java`.
    - Run `mvn clean install -X` for detailed MapStruct logs.
- **Database Connection Issues**: Verify MySQL is running and the credentials in `application.properties` are correct.
- **Lombok Issues**: Ensure the Lombok plugin is installed in your IDE and annotation processing is enabled.

## Contributing
Contributions are welcome! Please fork the repository, create a feature branch, and submit a pull request to `https://github.com/Code81Task/books`.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
