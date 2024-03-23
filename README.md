# Task Tracker Backend

## Overview

Task Tracker Backend is an application developed using Spring Boot, providing a REST API for managing users and their
tasks. The application offers functionality for user registration, authentication, task creation, editing, deletion, and
marking tasks as completed.

Additional project details can be found in the Task Tracker Info.  Please refer to it [here](https://github.com/Bityta/task-tracker-info).


## Features

### User Management:

- Registration: Allows users to register by providing necessary information such as email, password, and first name.
  Upon successful registration, the user is automatically logged in.
- Authentication: Supports user authentication through JWT (JSON Web Token). Users can obtain an access token by
  providing valid credentials.
- Logout: Enables users to invalidate their access tokens, effectively logging them out of the system.

### Task Management:

- Task Creation: Allows users to create tasks with a title and description.
- Task Modification: Permits users to edit existing tasks, including updating the title and description.
- Task Completion: Allows users to mark tasks as completed, providing a timestamp for when the task was marked as done.

### Error Handling:

- Provides clear and informative error messages in case of unsuccessful registration or authentication attempts.
- Uses appropriate HTTP status codes (4xx, 5xx) to indicate different error scenarios, ensuring proper client-side
  handling.

### Security:

- Utilizes JWT tokens for secure authentication, ensuring that only authenticated users can access protected resources.
- Implements Spring Security to manage user authentication and authorization at the application level.
- Protects sensitive endpoints such as user registration and task management with proper access controls.

### Database Integration:

- Utilizes PostgreSQL as the database management system for storing user and task data.
- Defines relational database schemas for users and tasks, ensuring data integrity and consistency.
- Integrates Flyway for database schema management, allowing for seamless migrations and version control.

### Logging:

Logs application events and errors to facilitate troubleshooting and monitoring.
Supports log rotation and management to prevent log file clutter and optimize storage usage.

### Deployment:

- Configures application profiles for different environments (e.g., development, production) using Spring profiles.
- Supports deployment to cloud platforms or on-premises servers using Docker containers for easy scalability and
  management.

### Service Discovery (Eureka):

- Registers the application with a service registry (Eureka) for service discovery and dynamic routing in a
  microservices architecture.
- Allows seamless integration with other microservices within the ecosystem for enhanced functionality and
  interoperability.

## Getting Started

Before starting the `task-tracker-backend` microservice, ensure that the `task-tracker-email-sender` microservice is up
and running, as it is required for proper functionality.

You can find the `task-tracker-email-sender` microservice [here](https://github.com/Bityta/task-tracker-email-sender).

To run the application locally using Docker Compose, follow these steps:

1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd task-tracker-scheduler`
3. Configure your desired profile by uncommenting the corresponding section in the application.yml file.
4. Build the Docker image: `docker-compose build`
5. Run the Docker container: `docker-compose up`

This will build the Docker image for the application and start the container.

Once the container is up and running, the application will be accessible at `http://localhost:8080`.

To test the performance, you can use swagger, to do this, go to http://localhost:8080/swagger-ui/index.html#/

### Profiles

- **ide**: This profile is used for local development. It is configured to run on port `8080` with an PostgreSQL
  database. The database URL is `jdbc:postgresql://localhost:15432/task-tracker-backend`. You will need to provide
  username, and password.

- **prod**: This profile is used for production deployment. It runs on port `8080` as well but connects to a PostgreSQL
  database. You will need to provide username, and password.

To select a profile, go to application.yml and change the spring:

```yaml
application:
  profiles:
    active: your-profile
  ```
Path to the application.yml file: `src\main\resources`


Replace your-profile with an ide or prod

Here's an example configuration:

```yaml
database:
  username: your-username
  password: your-password
```

Replace your-username, and your-password with the actual URL, username, and password for your PostgreSQL database.

## Dependencies

- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Cloud Eureka Client

## Contributing

Contributions are welcome! Feel free to open issues or pull requests.
