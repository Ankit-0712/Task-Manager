# Task Manager App

A full-stack **Task Manager** application built with **Spring Boot** (backend), **Angular** (frontend), and **PostgreSQL** (database) for managing users and tasks efficiently.

---

## Features

* CRUD operations for Users and Tasks
* Assign tasks to users
* Track task status (Pending, In Progress, Completed)
* Responsive UI with Angular
* Field validations

---

## Tech Stack

| Layer       | Technology                         |
| ----------- | ---------------------------------- |
| Frontend    | Angular 17+, HTML, CSS, TypeScript |
| Backend     | Spring Boot 3, Java 17             |
| Database    | PostgreSQL 15+                     |
| ORM         | Spring Data JPA (Hibernate)        |
| HTTP Client | Angular HttpClient                 |
| Tools       | VS Code, IntelliJ IDEA, Postman    |

---

## Folder Structure

```
taskmanager/
├── backend/       # Spring Boot backend
│   ├── src/main/java/com/example/taskmanager
│   ├── pom.xml
├── frontend/      # Angular frontend
│   ├── src/app
│   ├── angular.json
└── README.md
```

---

## Prerequisites

* Java 17, Node.js 18+, Angular CLI 17+
* PostgreSQL 15+
* IntelliJ IDEA (backend), VS Code (frontend)

---

## 🗄️ PostgreSQL Setup

1. **Install PostgreSQL**  
   Download and install PostgreSQL from [https://www.postgresql.org/download/](https://www.postgresql.org/download/) and start the PostgreSQL server.

2. **Create a database**  
   Open `psql` (PostgreSQL CLI) or use pgAdmin and run:

   ```sql
   CREATE DATABASE taskmanager;

## Backend Setup

```bash
cd taskmanager/backend
```

Configure `application.properties` with your DB credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=USER
spring.datasource.password=PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

Build & run:

```bash
mvn clean install
mvn spring-boot:run
```

Backend runs at: `http://localhost:8080/api/`

---

## Frontend Setup

```bash
cd taskmanager/frontend
npm install
ng serve
```

Frontend runs at: `http://localhost:4200/`

> Ensure backend is running before using frontend.

---

## API Endpoints

**Users:** GET `/api/users`, GET `/api/users/{id}`, POST `/api/users`, PUT `/api/users/{id}`, DELETE `/api/users/{id}`

**Tasks:** GET `/api/tasks`, GET `/api/tasks/{id}`, POST `/api/tasks`, PUT `/api/tasks/{id}`, DELETE `/api/tasks/{id}`

---

## Usage

* Add users via **Add User** button
* Create tasks via **Add Task**
* Edit/delete users and tasks
* Update task status in the task form

---

## Future Improvements

* Unit tests for frontend & backend
* Authentication & Authorization (JWT)
* Filtering, search, task priority, deadlines

## 🧪 Unit Tests

All backend unit test cases are documented in [Unit Test Cases](src/com.example.taskmanager/docs/unit-test-cases.md).  
You can run all tests with:

```bash
cd backend
mvn test

