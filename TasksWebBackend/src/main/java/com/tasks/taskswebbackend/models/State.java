package com.tasks.taskswebbackend.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="state")
public class State {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name",nullable = false, unique = false, length=16)
    private String name;
    @Column(name="description",nullable = false, unique = false, length=128)
    private String description;

    //Relations

    //Constructors
    public State(){}

    public State(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //Getters and Setters -> using Lombok

}
