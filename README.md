Employee Entity with Annotations - Spring Boot Project
Overview
This project demonstrates a Spring Boot application that manages employee data using Postgresql relational database, while using Hibernate annotation to design a database table(Entity) with atleast 10 fields. The Employee class represents an entity with fields like id, firstName, lastName, email, profilePicture, address, gender, and timestamps for creation and modification.

Dependencies
The project uses the following dependencies:

Spring Web: To build RESTful web applications.
Spring Boot: To create stand-alone Spring applications.
Spring Data JPA: For working with relational databases using JPA.
Hibernate: ORM tool for mapping Java objects to relational database tables.
Lombok: To reduce boilerplate code by generating constructors, getters, setters, etc.
PostgreSQL relational database: The database where employee data is persisted.
Class Annotations
@Entity
This annotation specifies that the Employee class is an entity, meaning it is mapped to a database table. In this case, each instance of the class corresponds to a row in the employees table.

@Table(name = "employees")
Specifies the table name in the database that this entity maps to. In this case, the employees table is explicitly defined.

@Id
This annotation marks the id field as the primary key of the entity.

@GeneratedValue(strategy = GenerationType.IDENTITY)
Defines the generation strategy for the primary key. The IDENTITY strategy indicates that the database will automatically generate and manage the primary key values (usually for auto-increment fields).

@Column(name = "first_name")
This annotation defines the column name in the database that the field will be mapped to. Here, the firstName field is mapped to the first_name column. By default, column names are derived from the field names, but the name attribute can be used to customize it.

@Column(nullable = false, unique = true)
This annotation on the email field defines additional constraints:

nullable = false: The column cannot be null (i.e., email is mandatory).
unique = true: Ensures that the email address must be unique in the database.
@Lob
The @Lob annotation on the profilePicture field indicates that it is a "Large Object." This is often used for handling large data such as binary files or large strings. In this case, it's used to store image data in the form of a byte array.

@CreationTimestamp
This annotation automatically populates the createdDate field with the timestamp of when the entity was created. It is handled by Hibernate.

@UpdateTimestamp
Similarly, the updatedDate field will be automatically updated to the current timestamp when the entity is updated.

Lombok Annotations
@Data
This is a Lombok annotation that generates boilerplate code, such as getters, setters, toString(), hashCode(), and equals() methods for the class, reducing the amount of code written manually.

@AllArgsConstructor
Generates a constructor with parameters for all the fields in the class.

@NoArgsConstructor
Generates a no-argument constructor, which is necessary for JPA to instantiate the entity.

Spring Boot Setup
application.properties
Ensure you have configured your database settings in the application.properties or application.yml file. Here is an example:

spring.application.name=flexisafinternshipapp
spring.datasource.url=jdbc:postgresql://localhost:5432/ems
spring.datasource.username=postgres
spring.datasource.password=jarule
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
Main Application Class
@SpringBootApplication
public class EmployeeManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }
}
@SpringBootApplication: This annotation is used to mark the main class of a Spring Boot application. It combines the functionality of @Configuration, @EnableAutoConfiguration, and @ComponentScan.
SpringApplication.run: This method launches the application.

# Spring Boot Application with Flyway Database Migrations

The project has included demonstration on how to use Flyway for managing database schema migrations in a Spring Boot application. Flyway is configured to handle schema changes automatically through versioned SQL migration scripts.

## Features
- **Spring Boot** and **Flyway** integration for database migration.
- Initial migration to create the `employees` table.
- A second migration to add the `age` column to the `employees` table.

## Technologies Used
- Spring Boot
- Flyway for database migrations
- PostgreSQL (or any other RDBMS)

## Database Migrations

Flyway is set up to manage schema changes using SQL migration scripts located in the `src/main/resources/db/migration/` directory.

### 1. Initial Migration (`V1__Create_employees_table.sql`)
The first migration script creates the `employees` table with the following fields:
- `id` (Primary Key)
- `first_name`
- `last_name`
- `email`
- `username`
- `profile_picture`
- `address`
- `gender`
- `created_date` (Automatically generated timestamp)
- `updated_date` (Automatically updated timestamp)

**Migration Script**: [`V1__Create_employees_table.sql`](src/main/resources/db/migration/V1__Create_employees_table.sql)

```sql
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    user_name VARCHAR(255),
    profile_picture LONGBLOB,
    address VARCHAR(255),
    gender VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

