package com.tasks.taskswebbackend.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
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
    private String last_name;
    @Column(name="user_name",nullable = false, unique = true, length=16)
    private String user_name;
    @Column(name="email",nullable = false, unique = true, length=50)
    private String email;

    //Relations and constrains
    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile profile_id;


    //Constructors
    public User(){}

    public User(Long id, String name, String last_name, String user_name, String email, Profile profile_id) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.email = email;
        this.profile_id = profile_id;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String lastname) {
        this.last_name = lastname;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Profile getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Profile profile_id) {
        this.profile_id = profile_id;
    }
}
