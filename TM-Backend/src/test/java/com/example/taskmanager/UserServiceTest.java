package com.example.taskmanager;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // CREATE
    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");

        when(userRepository.save(user)).thenReturn(user);

        User saved = userService.createUser(user);

        assertNotNull(saved);
        assertEquals("John", saved.getFirstName());
        assertEquals("Doe", saved.getLastName());
        assertEquals("john@example.com", saved.getEmail());

        verify(userRepository, times(1)).save(user);
    }

    // READ
    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Jane");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User found = userService.getUserById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
        assertEquals("Jane", found.getFirstName());

        verify(userRepository, times(1)).findById(1L);
    }

    // UPDATE
    @Test
    void testUpdateUser() {
        // existing user from DB
        User existing = new User();
        existing.setId(1L);
        existing.setFirstName("Old");
        existing.setLastName("Name");
        existing.setEmail("old@example.com");

        // new user data to update
        User updated = new User();
        updated.setFirstName("New");
        updated.setLastName("User");
        updated.setEmail("new@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.updateUser(1L, updated);

        assertNotNull(result);
        assertEquals("New", result.getFirstName());
        assertEquals("User", result.getLastName());
        assertEquals("new@example.com", result.getEmail());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existing);
    }

    // DELETE
    @Test
    void testDeleteUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
