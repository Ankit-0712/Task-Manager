package com.example.taskmanager.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {
    private String title;
    private String description;
    private String status;
    private Long assignedToId; // Only user ID, not full object
}
