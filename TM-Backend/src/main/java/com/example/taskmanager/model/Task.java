package com.example.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;


@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private  Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;

    @NotBlank(message = "Status is mandatory")
    private String status; //e.g.pending, in progress, completed

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    //mandatory assigned user
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("tasks") // ignore the tasks list inside user to avoid recursion
    private User assignedTo;

    //constructor for creating a task without id (for builders)
    public Task(String title, String description, String status, User assignedTo){
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    //update timeStamp when task is modify
    @PreUpdate
    public void preUpdate(){
        this.updatedAt = ZonedDateTime.now(ZoneOffset.UTC);


    }

    @PrePersist
    public void prePersist(){
        this.createdAt = ZonedDateTime.now(ZoneOffset.UTC);
        this.updatedAt = ZonedDateTime.now(ZoneOffset.UTC);
    }


}
