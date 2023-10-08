package com.tasks.taskswebbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude="tasks")
@Entity
@Table(name="task_state")
public class Task_state {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name",nullable = false, unique = false, length=16)
    private String name;
    @Column(name="description",nullable = false,unique = false, length=126)
    private String description;

    //Relations

    @OneToMany(mappedBy = "taskState")
    private Set<Task> tasks = new HashSet<>();

    //Constructors
    public Task_state(){}

    public Task_state(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //Getters and Setters -> using Lombok

}

