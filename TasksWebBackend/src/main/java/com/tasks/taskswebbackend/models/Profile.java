package com.tasks.taskswebbackend.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="profile")
public class Profile {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name", nullable = false,unique = false, length=16)
    private String name;
    @Column(name="role", nullable = false,unique = false, length=16)
    private String role;

    //Relations
    @ManyToOne
    @JoinColumn(name="state_id")
    private State state_id;

    //constructors
    public Profile(){}

    public Profile(Long id, String name, String role, State state_id) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.state_id = state_id;
    }

    //Getters and Setters -> using Lombok


}
