Sure! Here's a general README template for your Kotlin project, excluding the Employee part. You can customize it further based on the specifics of your project.

---

# Fretron Project

## Overview

The Fretron project is a Kotlin-based application for managing user authentication and CRUD operations. It includes functionality for user signup, login, updating, and deleting, with role-based access control. The project uses MongoDB for data storage and JWT for authentication.

## Features

- **User Signup**: Allows users to sign up with a `Non-Admin` role.
- **User Login**: Authenticates users and issues JWT tokens.
- **CRUD Operations**: Admins can create, read, update, and delete users.
- **JWT Authentication**: Secures API endpoints and validates user tokens.

## Prerequisites

- **Kotlin**: Ensure you have Kotlin installed.
- **MongoDB**: Make sure MongoDB is installed and running.
- **Grizzly HTTP Server**: Configured for server setup.
- **Kafka**: For messaging (if integrated).
- **Docker**: For containerization (if applicable).

## Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/Fretron.git
   cd Fretron
   ```

2. **Set Up MongoDB**

   Ensure MongoDB is running locally or configure it to point to your MongoDB instance.

3. **Set Up Docker (Optional)**

   If you are using Docker, ensure Docker is installed and running. You might need to set up containers for MongoDB, Kafka, etc.

4. **Build the Project**

   Run the following Gradle command to build the project:

   ```bash
   ./gradlew build
   ```

5. **Run the Application**

   Start the server with:

   ```bash
   ./gradlew run
   ```

## API Endpoints

### Signup

- **URL**: `/api/signup`
- **Method**: `POST`
- **Body**:
  ```json
  {
    "name": "User Name",
    "email": "user@example.com",
    "password": "password",
    "role": "NON_ADMIN"
  }
  ```

### Login

- **URL**: `/api/login`
- **Method**: `POST`
- **Body**:
  ```json
  {
    "email": "user@example.com",
    "password": "password"
  }
  ```

### Create User

- **URL**: `/api/createUser`
- **Method**: `POST`
- **Headers**:
  - `Authorization`: `Bearer <JWT_TOKEN>`
- **Body**:
  ```json
  {
    "name": "Admin User",
    "email": "admin@example.com",
    "password": "password",
    "role": "ADMIN"
  }
  ```

### Update User

- **URL**: `/api/updateUser/{email}`
- **Method**: `PUT`
- **Headers**:
  - `Authorization`: `Bearer <JWT_TOKEN>`
- **Body**:
  ```json
  {
    "password": "new_password",
    "role": "NON_ADMIN"
  }
  ```

### Delete User

- **URL**: `/api/deleteUser/{email}`
- **Method**: `DELETE`
- **Headers**:
  - `Authorization`: `Bearer <JWT_TOKEN>`


### Get All Users

- **URL**: `/api/getAllUsers`
- **Method**: `GET`
- **Headers**:
  - `Authorization`: `Bearer <JWT_TOKEN>`

## Configuration

Configuration settings are handled using environment variables or configuration files. Ensure the following environment variables are set:

- `MONGO_URI`: MongoDB connection URI.
- `JWT_SECRET`: Secret key for JWT.

## Testing

To run tests:

```bash
./gradlew test
```
