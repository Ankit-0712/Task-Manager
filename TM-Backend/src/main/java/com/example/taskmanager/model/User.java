package com.example.taskmanager.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String timezone; //store user's timeZone

    @Column(nullable = false)
    private Boolean isActive;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    //one user can have many tasks assigned
    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> tasks  = new ArrayList<>();

}
