package com.tasks.taskswebbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(exclude = "users")
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
    private State state;


    @OneToMany(mappedBy = "profile")
    @JsonBackReference
    private List<User> users;


    //constructors
    public Profile(){}

    public Profile(Long id, String name, String role, State state) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.state = state;
    }

    //Getters and Setters -> using Lombok


}
