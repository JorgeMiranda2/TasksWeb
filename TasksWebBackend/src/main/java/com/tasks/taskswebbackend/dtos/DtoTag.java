package com.tasks.taskswebbackend.dtos;

import lombok.Data;

@Data
public class DtoTag {
    private String name;
    private String description;

    public DtoTag(String name, String description){
        this.name = name;
        this.description = description;
    }
}
