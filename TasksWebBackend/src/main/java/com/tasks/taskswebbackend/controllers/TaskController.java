package com.tasks.taskswebbackend.controllers;

import com.tasks.taskswebbackend.models.Profile;
import com.tasks.taskswebbackend.models.Tag;
import com.tasks.taskswebbackend.models.Task;
import com.tasks.taskswebbackend.models.Task;
import com.tasks.taskswebbackend.services.TagService;
import com.tasks.taskswebbackend.services.TaskService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api")
public class TaskController {

    //Revisar si hay otro metodo del repo para detectar si existe un registro con ese id que no devuelva el registro, solo un true o false
    private final TaskService taskService;
    private final TagService tagService;


    public TaskController(TaskService taskService, TagService tagService) {
        this.taskService = taskService;
        this.tagService = tagService;
    }

    @PostMapping("/task")
    public ResponseEntity<String> createTask(@RequestBody Task task){
        System.out.println("Getting a post request to task");

        //Managing headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "Application/json");

        System.out.println(task);
        //Saving the task by service
        Long taskId = taskService.save(task);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(taskId).toUri();
        //returning the response
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Task created in: " + location);
    }

    @PutMapping("/task/{taskId}/tag/{tagId}")
    public ResponseEntity<String> addTagToTask(@PathVariable Long taskId, @PathVariable Long tagId){
        System.out.println("Getting a post request to task");

        Optional<Task> taskSaved = taskService.getTaskId(taskId);
        Optional<Tag> tagSaved = tagService.getTagId(tagId);
        if(taskSaved.isEmpty()  || tagSaved.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("task or tag id not found");
        }
        taskSaved.get().addTag(tagSaved.get());
        taskService.save(taskSaved.get());

        //returning the response
        return ResponseEntity.status(HttpStatus.OK).body("Tag created in the task with id:" + taskId);
    }

    @GetMapping("/task")
    public ResponseEntity<Collection<Task>> listTask(){
        System.out.println("Getting a get request to task");

        //Managing headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "Application/json");

        //Getting all tasks by service
        Collection<Task> tasks = taskService.list();
        System.out.println(tasks);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(tasks);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> listUserById(@PathVariable Long id){
        System.out.println("Getting by id");
        Optional<Task> userObtained = taskService.getTaskId(id);
        return userObtained.map(task -> ResponseEntity.status(HttpStatus.OK).body(task))
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());

    }

    @GetMapping("/task/{id}/tags")
    public ResponseEntity<Collection<Tag>> listUserTasksById(@PathVariable Long id){
        System.out.println("Getting Task tasks by task id");
        Optional<Task> taskObtained = taskService.getTaskId(id);
        if(taskObtained.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskObtained.get().getTags());
    }



    @PutMapping("/task/{id}")
    public ResponseEntity<String> updateUser(@Valid @PathVariable Long id, @RequestBody Task task){
        Optional<Task> isTask = taskService.getTaskId(id);
        if(isTask.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        System.out.println(task);
        taskService.save(task);
        return ResponseEntity.status(HttpStatus.OK).body("Register updated!");
    }

    @DeleteMapping("/task/{id}")

    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        Optional<Task> isTask = taskService.getTaskId(id);
        if(isTask.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        taskService.delete(isTask.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted");
    }

}
