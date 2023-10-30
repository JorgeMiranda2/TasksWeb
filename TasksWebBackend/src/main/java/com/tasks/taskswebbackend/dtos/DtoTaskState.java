package com.tasks.taskswebbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class DtoTaskState {
private Long id;
private String name;
private String description;

public DtoTaskState(){}
    public DtoTaskState(Long id){
    this.id = id;
    }
}
