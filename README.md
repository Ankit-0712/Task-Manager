Task Manager App
A full-stack Task Manager application built with Spring Boot (backend), Angular (frontend), and PostgreSQL (database). Users can be created, assigned tasks, and manage tasks efficiently with CRUD operations.

Features

User Management

Create, Read, Update, Delete (CRUD) users

Assign tasks to users

Task Management

Create, Read, Update, Delete tasks

Assign tasks to existing users

Track task status (Pending, In Progress, Completed)

View all users with their tasks

Responsive UI using Angular

Validation for required fields

Tech Stack
Layer	Technology
Frontend	Angular 17+, HTML, CSS, TypeScript
Backend	Spring Boot 3, Java 17
Database	PostgreSQL 15+
ORM	Spring Data JPA (Hibernate)
HTTP Client	Angular HttpClient
Tools	VS Code (frontend), IntelliJ IDEA (backend), Postman
Folder Structure
taskmanager/
├── backend/       # Spring Boot backend
│   ├── src/main/java/com/example/taskmanager
│   ├── pom.xml
├── frontend/      # Angular frontend
│   ├── src/app
│   ├── angular.json
└── README.md

Prerequisites

Java 17

Node.js 18+ & npm

Angular CLI 17+

PostgreSQL 15+

IDEs: IntelliJ IDEA (backend), VS Code (frontend)

Backend Setup (Spring Boot)

Navigate to the backend folder:

cd taskmanager/backend


Configure application.properties for PostgreSQL:

spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=YOUR_DB_USER
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


Build and run the backend:

mvn clean install
mvn spring-boot:run


The backend will run at:

http://localhost:8080/api/

Frontend Setup (Angular)

Navigate to the frontend folder:

cd taskmanager/frontend


Install dependencies:

npm install


Run the Angular app:

ng serve


The frontend will run at:

http://localhost:4200/


Make sure the backend is running before using the frontend.

API Endpoints
Users
Method	URL	Description
GET	/api/users	Get all users
GET	/api/users/{id}	Get user by ID
POST	/api/users	Create new user
PUT	/api/users/{id}	Update user
DELETE	/api/users/{id}	Delete user
Tasks
Method	URL	Description
GET	/api/tasks	Get all tasks
GET	/api/tasks/{id}	Get task by ID
POST	/api/tasks	Create new task
PUT	/api/tasks/{id}	Update task
DELETE	/api/tasks/{id}	Delete task
Usage

Add new users using the Add User button.

Create tasks for users using Add Task.

View tasks assigned to each user in the User List.

Edit or delete users and tasks using the respective buttons.

Task status can be updated directly from the task form.

Future Improvements

Add unit tests for backend and frontend.

Add authentication & authorization (Spring Security + JWT).

Add filtering & search for users and tasks.

Add task priority & deadlines.
