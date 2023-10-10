package com.tasks.taskswebbackend.services;

import com.tasks.taskswebbackend.interfaceservices.ITaskStateService;
import com.tasks.taskswebbackend.models.TaskState;
import com.tasks.taskswebbackend.repositories.ITaskStateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TaskStateService implements ITaskStateService {
    private  final ITaskStateRepo taskStateRepo;

    @Autowired
    public TaskStateService(ITaskStateRepo taskStateRepo) {
        this.taskStateRepo = taskStateRepo;
    }

    @Override
    public Collection<TaskState> list(){
        Collection<TaskState> taskStates;
        taskStates = taskStateRepo.findAll();
        return taskStates;
    }
    @Override
    public Optional<TaskState> getTaskStateId(Long id){
        return taskStateRepo.findById(id);
    }
    @Override
    public Long save(TaskState taskState){
        TaskState savedState = taskStateRepo.save(taskState);
        return savedState.getId();
    }
    @Override
    public void delete(TaskState taskState){
        taskStateRepo.delete(taskState);
    }

}
