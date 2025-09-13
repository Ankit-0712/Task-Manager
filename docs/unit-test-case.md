# 🧪 Unit Test Cases

This document lists all the unit test cases implemented for the **Task Manager REST API** project.  
These tests focus on **User CRUD operations** using **JUnit 5 and Mockito**.

---

## 📁 UserServiceTest

**Location:** `src/test/java/com/example/taskmanager/UserServiceTest.java`

| Test Case ID | Test Method           | Description                                      | Input Data                              | Expected Result                                      |
|--------------|------------------------|---------------------------------------------------|------------------------------------------|----------------------------------------------------------|
| TC_U1        | testCreateUser         | Should create and return a new user               | `User` object with firstName             | Returns saved `User` with same `firstName` and generated ID |
| TC_U2        | testGetUserById        | Should fetch an existing user by ID               | Valid `userId`                           | Returns `User` with matching `id`                          |
| TC_U3        | testDeleteUser         | Should delete user if exists                      | Valid `userId`                           | Verifies `deleteById()` is called once                      |
| TC_U4        | testUpdateUser         | Should update and return modified user details    | Valid `userId` and updated `User` fields | Returns updated `User` object with new field values         |

---

## ⚙️ Testing Frameworks
- **JUnit 5** for writing test cases
- **Mockito** for mocking the `UserRepository`
- `@Mock` and `@InjectMocks` annotations used

---

## 📌 Notes
- The `UserRepository` is mocked to isolate service layer logic.
- `when(...)` is used to simulate repository responses.
- `verify(...)` is used to ensure repository methods are called as expected.
- Coverage includes: **Create, Read, Update, Delete** operations.

---
