package com.tasks.taskswebbackend.dtos;

import com.tasks.taskswebbackend.models.Task;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DtoTask {
 private Long id;
 private Date startDate;
 private Date endDate;
 private String title;
 private String description;
 private DtoTaskState taskState;
 private List<DtoTag> tags;


public DtoTask(){}
    public DtoTask(Task task){
     this.id = task.getId();
     this.startDate = task.getStartDate();
     this.endDate = task.getEndDate();
     this.title = task.getTitle();
     this.description = task.getDescription();
     this.taskState = new DtoTaskState(task.getTaskState());
     this.tags = task.getTags().stream().map((tag) -> {
         return new DtoTag(tag.getName(), tag.getDescription(), tag.getId());
     }).collect(Collectors.toList());
 }


}
