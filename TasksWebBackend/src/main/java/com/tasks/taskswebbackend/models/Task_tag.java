package com.tasks.taskswebbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="task_tag")
public class Task_tag {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="creation_date",nullable = false, unique = false)
    private Date creation_date;

    //Relations

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @ManyToOne
    @JoinColumn(name="tag_id", referencedColumnName = "id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="state_id")
    private State state_id;
}
