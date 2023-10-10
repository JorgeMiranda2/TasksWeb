package com.tasks.taskswebbackend.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString(exclude="tasks")
@Entity
@Table(name = "user")
public class User {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name",nullable = false, unique = false, length=40)
    private String name;
    @Column(name="last_name",nullable = false, unique = false, length=40)
    private String lastName;
    @Column(name="user_name",nullable = false, unique = true, length=16)
    private String userName;
    @Column(name="email",nullable = false, unique = true, length=50)
    private String email;

    //Relations and constrains
    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;


    //Constructors
    public User(){}



    //Getters and Setters

}
