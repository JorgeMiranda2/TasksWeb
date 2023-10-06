package com.tasks.taskswebbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
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
    private Date start_date;
    @Column(name="end_date")
    private Date end_date;

    //Relations
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name="task_state_id")
    private Task_state task_state_id;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Task_tag> task_tags = new HashSet<>();



    //Constructors
    public Task(){}

    public Task(Long id, String title, String description, Date start_date, Date end_date, State state_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.state_id = state_id;
    }

    //Getters and Setters -> using Lombok

}
