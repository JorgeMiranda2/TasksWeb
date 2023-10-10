package com.tasks.taskswebbackend.controllers;

import com.tasks.taskswebbackend.models.TaskState;
import com.tasks.taskswebbackend.services.TaskStateService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api")
public class TaskStateController {

    //Revisar si hay otro metodo del repo para detectar si existe un registro con ese id que no devuelva el registro, solo un true o false
    private final TaskStateService taskStateService;

    public TaskStateController(TaskStateService taskStateService) {
        this.taskStateService = taskStateService;
    }

    @PostMapping("/taskstate")
    public ResponseEntity<String> createState(@RequestBody TaskState taskState){
        System.out.println("Getting a post request to taskState");

        //Managing headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "Application/json");

        System.out.println(taskState);
        //Saving the taskState by service
        Long taskStateId = taskStateService.save(taskState);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(taskStateId).toUri();
        //returning the response
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("TaskState created in: " + location);
    }

    @GetMapping("/taskstate")
    public ResponseEntity<Collection<TaskState>> listTaskState(){
        System.out.println("Getting a get request to taskState");

        //Managing headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "Application/json");

        //Getting all taskStates by service
        Collection<TaskState> taskStates = taskStateService.list();
        System.out.println(taskStates);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(taskStates);
    }

    @GetMapping("/taskstate/{id}")
    public ResponseEntity<TaskState> listTaskStateById(@PathVariable Long id){
        System.out.println("Getting by id");
        Optional<TaskState> taskStateObtained = taskStateService.getTaskStateId(id);
        return taskStateObtained.map(taskState -> ResponseEntity.status(HttpStatus.OK).body(taskState))
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());

    }



    @PutMapping("/taskstate/{id}")
    public ResponseEntity<String> updateTaskState(@Valid @PathVariable Long id, @RequestBody TaskState taskState){
        Optional<TaskState> isTaskState = taskStateService.getTaskStateId(id);
        if(isTaskState.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        System.out.println(taskState);
        taskStateService.save(taskState);
        return ResponseEntity.status(HttpStatus.OK).body("TaskState updated!");
    }

    @DeleteMapping("/taskstate/{id}")

    public ResponseEntity<String> deleteTaskState(@PathVariable Long id){
        Optional<TaskState> isTaskState = taskStateService.getTaskStateId(id);
        if(isTaskState.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        taskStateService.delete(isTaskState.get());
        return ResponseEntity.status(HttpStatus.OK).body("TaskState deleted");
    }

}
