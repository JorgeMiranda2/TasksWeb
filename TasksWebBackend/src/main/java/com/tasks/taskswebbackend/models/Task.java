package com.tasks.taskswebbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tasks.taskswebbackend.dtos.DtoTask;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.swing.text.html.Option;
import java.util.*;

@Data
@Entity
@ToString(exclude = "tags")
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
    private State state;

    @ManyToOne()
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name="task_state_id")
    private TaskState taskState;

    @ManyToMany
    @JoinTable(
            name="task_tag",
            joinColumns = @JoinColumn(name="task_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    private List<Tag> tags;

    //Constructors
    public Task(){}

    public Task(DtoTask dtoTask, Long id){
        this.user = new User(id);
        this.title = dtoTask.getTitle();
        this.description = dtoTask.getDescription();
        this.startDate = dtoTask.getStartDate();
        this.endDate = dtoTask.getEndDate();
        this.state = new State(1L);
        this.taskState = new TaskState(1L);
        this.tags = new ArrayList<>();

    }

    public Task(Long id, String title, String description, Date startDate, Date endDate, State state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }

    //Getters and Setters -> using Lombok

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

}
