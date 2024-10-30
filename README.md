Employee Management Spring Boot Application

This is a simple Employee Management RESTful API built with Spring Boot. It provides basic CRUD operations for managing employee records and demonstrates a layered architecture with exception handling, input validation, and well-structured service, repository, and controller layers.

Features

- **CRUD Endpoints**: Create, Read, Update, and Delete employees.
- **Layered Architecture**: Service, Repository, and Controller layers for clean separation of concerns.
- **Input Validation**: Ensures valid input data using `@Valid` and custom validations.
- **Exception Handling**: Custom exceptions for clear, consistent error messages and HTTP status codes.

Technologies Used

- Java 17
- Spring Boot 3.3.3
- Spring Data JPA
- Postgresql database
- Spring Validation

   The application will start on `http://localhost:8080`.

## API Endpoints

### 1. Create Employee

- **URL**: `/api/employees`
- **Method**: `POST`
- **Request Body**: JSON representation of the employee object

  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  }
  ```

- **Response**: `201 Created`

### 2. Get Employee by ID

- **URL**: `/api/employees/{id}`
- **Method**: `GET`
- **Response**: `200 OK` with JSON employee data or `404 Not Found` if the employee does not exist.

### 3. Get All Employees

- **URL**: `/api/employees`
- **Method**: `GET`
- **Response**: `200 OK` with a list of all employees.

### 4. Update Employee

- **URL**: `/api/employees/{id}`
- **Method**: `PUT`
- **Request Body**: JSON with updated employee data
- **Response**: `200 OK` if successful, `404 Not Found` if the employee does not exist.

### 5. Delete Employee

- **URL**: `/api/employees/{id}`
- **Method**: `DELETE`
- **Response**: `200 OK` with a confirmation message or `404 Not Found` if the employee does not exist.

## Layers

### 1. Controller Layer (`EmployeeController`)

Handles HTTP requests and responses for the `/api/employees` endpoints, delegating business logic to the service layer.

### 2. Service Layer (`EmployeeService`)

Contains business logic for managing employees and handles interactions with the repository.

### 3. Repository Layer (`EmployeeRepo`)

Extends `JpaRepository` to provide CRUD operations on the database.

## Exception Handling

Custom exceptions and a global exception handler ensure consistent error responses:

- **`EmployeeNotFoundException`**: Thrown when an employee with a given ID is not found.
- **Global Exception Handler**: Maps exceptions to appropriate HTTP status codes and messages.

## Input Validation

The application uses `@Valid` and custom constraints to validate inputs in the `EmployeeDto` class:

java


Invalid requests will automatically return a `400 Bad Request` with validation error messages.


