package com.tasks.taskswebbackend.dtos;

import com.tasks.taskswebbackend.models.TaskState;
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
    public DtoTaskState(TaskState taskState){
    this.id = taskState.getId();
    this.name = taskState.getName();
    this.description = taskState.getDescription();
    }
}
