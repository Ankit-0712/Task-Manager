package com.example.taskmanager.controller;

import com.example.taskmanager.DTO.UserDTO;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping(consumes = "application/json") // ✅ specify JSON
    public ResponseEntity<User> createUser(@RequestBody UserDTO dto) {
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .timezone(dto.getTimezone())
                .isActive(dto.getIsActive())
                .build();

        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Update existing user
    @PutMapping(value = "/{id}", consumes = "application/json") // ✅ specify JSON
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        User updatedUser = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .timezone(dto.getTimezone())
                .isActive(dto.getIsActive())
                .build();

        User savedUser = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(savedUser);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully with id " + id);
    }
}
