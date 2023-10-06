package com.tasks.taskswebbackend.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;
@Data
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
    private State state_id;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private Set<Task_tag> task_tags = new HashSet<>();

    //Constructors
    public Tag(){}

    public Tag(Long id, String name, String description, State state_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state_id = state_id;
    }

    //Getters and Setters -> using Lombok


}
