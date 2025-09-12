package com.example.taskmanager.service;

import com.example.taskmanager.DTO.TaskDTO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new task
    public Task createTask(TaskDTO dto) {
        User assignedUser = userRepository.findById(dto.getAssignedToId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getAssignedToId()));

        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .assignedTo(assignedUser)
                .build();

        return taskRepository.save(task);
    }

    // Update existing task
    public Task updateTask(Long id, TaskDTO dto) {
        User assignedUser = userRepository.findById(dto.getAssignedToId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getAssignedToId()));

        return taskRepository.findById(id).map(task -> {
            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setStatus(dto.getStatus());
            task.setAssignedTo(assignedUser);
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    // Delete task
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }
}
