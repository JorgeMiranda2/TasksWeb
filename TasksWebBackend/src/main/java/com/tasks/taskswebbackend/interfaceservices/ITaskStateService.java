package com.tasks.taskswebbackend.interfaceservices;

import com.tasks.taskswebbackend.models.TaskState;

import java.util.Collection;
import java.util.Optional;


public interface ITaskStateService {
    public Collection<TaskState> list();
    public Optional<TaskState> getTaskStateId(Long id);
    public Long save(TaskState taskState);
    public void delete(TaskState taskState);

}
