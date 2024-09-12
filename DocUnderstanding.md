Below is the updated document with all topics, including AVRO and Kafka:

---

# **Kotlin Project: Overview and Topics**

## **Project Overview**
The Kotlin project involves building a server-side application with Signup, Login, and User management functionalities. The system has two types of users: `ADMIN` and `NON_ADMIN`. While Non-Admin users can only sign up and log in, Admin users can perform CRUD operations on users. The project uses MongoDB for data storage, Grizzly server for handling HTTP requests, and incorporates Dependency Injection (DI) using Dagger.

### **Tech Stack**
- **Kotlin**
- **MongoDB** (NoSQL Database)
- **Grizzly** (Server)
- **JWT** (for Authentication)
- **Dagger** (Dependency Injection)
- **AVRO** (Serialization)
- **Kafka** (Message Streaming)

---

## **Key Topics Covered**

### 1. **Server Setup**
- **Grizzly Server**: The Grizzly server is used to handle HTTP requests in the project. The server setup is done through `HttpModule` and the server is initialized using `Application.kt`.
- **Routing**: RESTful API routing is implemented for Signup, Login, and User operations using `@Path` and `@GET`, `@POST`, etc.

### 2. **RESTful APIs**
- **Signup API**: Non-Admin users can sign up by providing their Name, Email, and Password. The `Role` field defaults to `NON_ADMIN`.
- **Login API**: Users can log in with their email and password. On successful login, a JWT token is generated.
- **CRUD API for Admin**: Admin users can create, read, update, and delete other users. Non-Admin users don’t have access to these operations.

### 3. **JWT Authentication**
- **JWT Tokens**: Used for securing the application. A token is generated upon successful login and is used to validate user requests to protected routes.
- **Login Flow**: During login, the system generates a JWT, which contains claims like user role and expiration time.

### 4. **Database Integration (MongoDB)**
- **MongoDB Setup**: MongoDB is set up to store user details like Name, Email, Password, and Role.
- **User Repository**: The `UserRepository` class is used to interact with MongoDB for user-related operations like saving new users or fetching user data.
- **Partial Update**: Functionality for updating only certain fields of the user record without overwriting the entire document.

### 5. **Dependency Injection (DI)**
- **Dagger**: Used for DI to manage and inject dependencies such as `UserService`, `HttpServer`, etc.
- **Modules and Components**: `@Module`, `@Provides`, and `@Singleton` are used to define the DI graph.
- **@Named Annotations**: Used to inject different configurations, such as port numbers or other constants.

### 6. **Business Logic Separation (UserService)**
- **Service Layer**: The `UserService` class contains business logic for signup, login, and user operations. This separates the logic from API handling in `UserResources`.
- **Role-Based Access**: The service ensures that only Admins can perform CRUD operations, while Non-Admins are limited to Signup and Login.

### 7. **Project Structure**
- **Separation of Concerns**: The project structure is organized into various packages like `services`, `models`, and `resources` to keep the codebase modular and maintainable.
  - **Services**: Classes like `SignupHandler` and `LoginHandler`.
  - **Models**: Data classes like `SignupRequest`, `LoginRequest`.
  - **Resources**: API handling is done in resource classes like `UserResources`.

### 8. **Enum for User Roles**
- **Role Enum**: Defined to restrict the role values to either `ADMIN` or `NON_ADMIN`.

### 9. **Partial Update Functionality**
- **Update User**: Partial updates to user details (e.g., updating just the email or name) are allowed through the API.

### 10. **Singleton Pattern**
- **@Singleton**: Used for managing a single instance of classes such as repositories, ensuring they are not re-created unnecessarily.

### 11. **Configuration Management**
- **@Named Annotations**: Used to inject values from external sources, like XML files or constants.

### 12. **AVRO Serialization**
- **AVRO Model**: AVRO is used for serialization. The `Employee` model is created as a raw AVRO schema, which is converted into a final model during the build process. This model is then used for handling data exchange.

### 13. **Kafka Integration**
- **Kafka Setup**: Kafka is introduced into the project for message streaming and event-driven communication. A use case is implemented where a Kafka producer sends a welcome email upon user signup.
- **Schema Registry**: Used in conjunction with Kafka to manage AVRO schemas for messages.
- **Kafka Components**: Kafka topics, producers, and consumers are configured to manage signup events and send notifications.

---

This document now includes AVRO and Kafka along with the core functionalities of your project, maintaining the right level of detail.
