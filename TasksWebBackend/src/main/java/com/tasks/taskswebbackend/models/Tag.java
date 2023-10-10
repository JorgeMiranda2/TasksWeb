package com.tasks.taskswebbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
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
    private State state;
    

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name="task_tag",
            joinColumns = @JoinColumn(name="tag_id"),
            inverseJoinColumns = @JoinColumn(name="task_id")
    )
    private Set<Task> tasks= new HashSet<>();
    //Constructors
    public Tag(){}

    public Tag(Long id, String name, String description, State state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
    }

    //Getters and Setters -> using Lombok


}
