package com.tasks.taskswebbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private State stateProfile;


    @OneToMany(mappedBy = "profile")
    private Set<User> users = new HashSet<>();


    //constructors
    public Profile(){}

    public Profile(Long id, String name, String role, State stateProfile) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.stateProfile = stateProfile;
    }

    //Getters and Setters -> using Lombok


}
