package com.tasks.taskswebbackend.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
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


    //Constructors
    public Task_state(){}

    public Task_state(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //Getters and Setters -> using Lombok

}

