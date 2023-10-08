package com.tasks.taskswebbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString(exclude = "tasks")
@Entity
@Table(name="tag")
public class Tag {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name",nullable = false,unique = false, length=20)
    private String name;
    @Column(name="description",nullable = false,unique = false, length=126)
    private String description;

    //Relations
    @ManyToOne
    @JoinColumn(name="state_id")
    private State stateTag;


    @ManyToMany(mappedBy = "tags")
    private Set<Task> tasks= new HashSet<>();
    //Constructors
    public Tag(){}

    public Tag(Long id, String name, String description, State stateTag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stateTag = stateTag;
    }

    //Getters and Setters -> using Lombok


}
