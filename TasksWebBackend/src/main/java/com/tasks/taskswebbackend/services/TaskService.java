package com.tasks.taskswebbackend.services;

import com.tasks.taskswebbackend.interfaceservices.ITaskService;
import com.tasks.taskswebbackend.models.Task;
import com.tasks.taskswebbackend.repositories.ITaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    private  final ITaskRepo taskRepo;

    @Autowired
    public TaskService(ITaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public Collection<Task> list(){
        Collection<Task> tasks;
        tasks = taskRepo.findAll();
        return tasks;
    }
    @Override
    public Optional<Task> getTaskId(Long id){
        return taskRepo.findById(id);
    }
    @Override
    public Long save(Task task){
        Task savedTask = taskRepo.save(task);
        return savedTask.getId();
    }
    @Override
    public void delete(Task task){
        taskRepo.delete(task);
    }

    @Override
    public List<Task> getTasksByUserId(Long id){

        return taskRepo.getTasksByUserId(id);
    }



}
