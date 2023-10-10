package com.tasks.taskswebbackend.interfaceservices;

import com.tasks.taskswebbackend.models.Task;

import java.util.Collection;
import java.util.Optional;


public interface ITaskService {
    public Collection<Task> list();
    public Optional<Task> getTaskId(Long id);
    public Long save(Task task);
    public void delete(Task task);

}
