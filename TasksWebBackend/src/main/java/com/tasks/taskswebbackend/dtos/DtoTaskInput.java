package com.tasks.taskswebbackend.dtos;

import com.tasks.taskswebbackend.models.Task;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DtoTaskInput {
 private Date startDate;
 private Date endDate;
 private String title;
 private String description;
 private Long taskStateId;
 private List<Long> tagsId;


public DtoTaskInput(){}

}
