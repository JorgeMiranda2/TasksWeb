package com.tasks.taskswebbackend.dtos;

import lombok.Data;

@Data
public class DtoTag {
    private Long id;
    private String name;
    private String description;

    public DtoTag(String name, String description, Long id){
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
