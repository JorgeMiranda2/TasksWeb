package com.tasks.taskswebbackend.controllers;

import com.tasks.taskswebbackend.dtos.DtoTask;
import com.tasks.taskswebbackend.dtos.DtoTaskInput;
import com.tasks.taskswebbackend.models.*;
import com.tasks.taskswebbackend.services.TagService;
import com.tasks.taskswebbackend.services.TaskService;
import com.tasks.taskswebbackend.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/api")
public class TaskController {

    //Revisar si hay otro metodo del repo para detectar si existe un registro con ese id que no devuelva el registro, solo un true o false
    private final TaskService taskService;
    private final TagService tagService;
    private final UserService userService;


    public TaskController(TaskService taskService, TagService tagService, UserService userService) {
        this.taskService = taskService;
        this.tagService = tagService;
        this.userService = userService;
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

    //Adding a tag in a task
    @PutMapping("/task/{taskId}/tag/{tagId}")
    public ResponseEntity<String> addTagToTask(@PathVariable Long taskId, @PathVariable Long tagId){
        System.out.println("Getting a put request to task");

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
    @GetMapping("/mytasks")
    public ResponseEntity<List<DtoTask>> listUserTasks(){
        System.out.println("Getting Task by token");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<Long> userId = userService.getUserIdFromUserName(userName);
        System.out.println(userId.get());
        List<Task> tasksList = taskService.getTasksByUserId(userId.get());
        List<DtoTask> dtoTasksList = tasksList.stream().map((task) -> {
            return new DtoTask(task);
        }).collect(Collectors.toList());

        System.out.println(dtoTasksList);

        // con el username obtener el id del registro -> con el id obtener sus tasks
        return ResponseEntity.status(HttpStatus.OK).body(dtoTasksList);
    }
    @PostMapping("/mytasks")
    public ResponseEntity<String> postUserTasks(@Valid @RequestBody DtoTaskInput dtoTaskInput){


        System.out.println("adding Task by token");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        System.out.println("username:" + userName);
        Optional<Long> userId = userService.getUserIdFromUserName(userName);
        System.out.println(userId.get());

        System.out.println("description: " + dtoTaskInput);
        Task task = new Task(dtoTaskInput, userId.get());

        taskService.save(task);

        // con el username obtener el id del registro -> con el id obtener sus tasks
        return ResponseEntity.status(HttpStatus.OK).body("Task saved");
    }


    @DeleteMapping("/mytasks/{id}")
    public ResponseEntity<String> postUserTasks(@Valid @PathVariable Long id){

        System.out.println("deleting Task by token");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        System.out.println("username:" + userName);
        Optional<Long> userId = userService.getUserIdFromUserName(userName);
        System.out.println(userId.get());
        Optional<Long> userIdByTask = taskService.getUserNameIdByTaskId(id);
    if(userIdByTask.isPresent() && userId.isPresent()){
        if(userIdByTask.get().equals(userId.get())){
            Optional<Task> isTask = taskService.getTaskId(id);
            taskService.delete(isTask.get());
            return ResponseEntity.status(HttpStatus.OK).body("task Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("delete Task forbidden, is your task?");
        }
    }


        // con el username obtener el id del registro -> con el id obtener sus tasks
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Something go wrong");
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
