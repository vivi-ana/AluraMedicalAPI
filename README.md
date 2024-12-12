# Healthcare Management API

**This API REST manages healthcare services, providing endpoints for user login, 
handles JWT token generation and validation and supports paginated listing of medical professionals and patients**

## Features

- **User Authentication**: Endpoints for user login and JWT token generation.
- **Doctor Management**: Endpoints for registering, listing, updating, and logically deleting doctors.
- **Patient Management**: Endpoints for registering, listing, updating, and logically deleting patients.
- **Appointment Management**: 
  - **Schedule Appointments**: Endpoints for scheduling new appointments. 
  - **Cancel Appointments**: Endpoints for canceling existing appointments.

## Technologies Used

- **Java 17**
- **Spring Boot with JPA, validations and security**
- **Lombok**
- **Flyway**:
- **JWT - Auth0**
- **Spring Doc**
- **DB: MySQL**

## Setup
Create a `.env` file in the root directory of the application and add the following database connection configuration:

```properties
DB_HOST=
DB_NAME=
DB_USERNAME=
DB_PASSWORD=
SECRET_KEY=
```

**Ensure that the database connection details in the .env file are correctly filled in before running the application.**

* DB_HOST: It is the host or the IP address of the database server that the application will connect to in order to access the data. Typically, this could be something like localhost if the database is on the same machine as the application, or the IP address of the database server if it is on a remote server.
* DB_NAME: This value corresponds to the specific name of the database that contains the tables and data that the application needs to function correctly.
* DB_USERNAME: It is the username that the application will use to connect to the database.
* DB_PASSWORD: It is the password associated with the username specified above.
* SECRET_KEY= A unique key used to sign and verify JWT tokens. Ensure you create your own key.

## Endpoints

### Authentication

- **POST /login**: Authenticates a user and generates a JWT token.

### Doctor Management

- **POST /doctor**: Registers a new doctor.

- **GET /doctor**: Retrieves a paginated list of active doctors.

- **PUT /doctor**: Updates a doctor's information.

- **DELETE /doctor/{id}**: Marks a doctor as inactive.

- **GET /doctor/{id}**: Retrieves detailed data of a specific doctor.

### Patient Management

- **POST /patient**: Registers a new patient.

- **GET /patient**: Retrieves a paginated list of patients.

- **PUT /patient**: Updates a patient's information.

- **DELETE /patient/{id}**: Marks a patient as inactive.

- **GET /patient/{id}**: Retrieves detailed data of a specific patient.

## Documentation

The project is documented using Javadoc. You can access to two versions:

1. **Javadoc with UI**: Accessible via a web browser for a more user-friendly experience. Navigate to:
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

2. **JSON Format**: This version provides documentation in JSON format. You can access it here:
   [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

**Note:** The `localhost:8080` address is used for local development and may vary depending on your environment and may vary depending on your environment and server configuration.





