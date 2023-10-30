package com.tasks.taskswebbackend.dtos;

import com.tasks.taskswebbackend.models.Task;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DtoTask {
 private Date startDate;
 private Date endDate;
 private String title;
 private String description;
 private String taskStateName;
 private List<DtoTag> tags;


public DtoTask(){}
    public DtoTask(Task task){
     this.startDate = task.getStartDate();
     this.endDate = task.getEndDate();
     this.title = task.getTitle();
     this.description = task.getDescription();
     this.taskStateName = task.getTaskState().getName();
     this.tags = task.getTags().stream().map((tag) -> {
         return new DtoTag(tag.getName(), tag.getDescription(), tag.getId());
     }).collect(Collectors.toList());

 }
}
