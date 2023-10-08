package com.tasks.taskswebbackend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "task")
public class Task {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title",nullable = false,unique = false, length=40)
    private String title;
    @Column(name="description",nullable = false,unique = false, length=126)
    private String description;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;

    //Relations
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State stateTask;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="task_state_id")
    private Task_state taskState;
    @ManyToMany
    @JoinTable(
            name="task_tag",
            joinColumns = @JoinColumn(name="task_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    private Set<Tag> tags= new HashSet<>();

    //Constructors
    public Task(){}

    public Task(Long id, String title, String description, Date startDate, Date endDate, State stateTask) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stateTask = stateTask;
    }

    //Getters and Setters -> using Lombok

}
