package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    //create a new task and assign it to a user
    public Task createTask(Task task, Long userId) {
        User assignedUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        task.setAssignedTo(assignedUser);
        return taskRepository.save(task);
    }

    //Get all tasks
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    //get task by ID
    public Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found with id" + id));
    }

    //update a task
    public Task updateTask(Long id, Task updatedTask, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            task.setAssignedTo(user); // update assigned user
            task.setUpdatedAt(ZonedDateTime.now(ZoneOffset.UTC));
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    //delete a task
    public void deleteTask(Long id){
        if(!taskRepository.existsById(id)){
            throw new RuntimeException("Task not found with id" + id);

        }

        taskRepository.deleteById(id);
    }

}
