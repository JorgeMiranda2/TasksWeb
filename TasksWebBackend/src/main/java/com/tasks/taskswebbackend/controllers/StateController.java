package com.tasks.taskswebbackend.controllers;

import com.tasks.taskswebbackend.models.State;
import com.tasks.taskswebbackend.services.StateService;
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
public class StateController {

    //Revisar si hay otro metodo del repo para detectar si existe un registro con ese id que no devuelva el registro, solo un true o false
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping("/state")
    public ResponseEntity<String> createState(@RequestBody State state){
        System.out.println("Getting a post request to state");

        //Managing headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "Application/json");

        System.out.println(state);
        //Saving the state by service
        Long stateId = stateService.save(state);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stateId).toUri();
        //returning the response
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("State created in: " + location);
    }

    @GetMapping("/state")
    public ResponseEntity<Collection<State>> listState(){
        System.out.println("Getting a get request to state");

        //Managing headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "Application/json");

        //Getting all states by service
        Collection<State> states = stateService.list();
        System.out.println(states);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(states);
    }

    @GetMapping("/state/{id}")
    public ResponseEntity<State> listStateById(@PathVariable Long id){
        System.out.println("Getting by id");
        Optional<State> stateObtained = stateService.getStateId(id);
        return stateObtained.map(state -> ResponseEntity.status(HttpStatus.OK).body(state))
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());

    }



    @PutMapping("/state/{id}")
    public ResponseEntity<String> updateState(@Valid @PathVariable Long id, @RequestBody State state){
        Optional<State> isState = stateService.getStateId(id);
        if(isState.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        System.out.println(state);
        stateService.save(state);
        return ResponseEntity.status(HttpStatus.OK).body("State updated!");
    }

    @DeleteMapping("/state/{id}")

    public ResponseEntity<String> deleteState(@PathVariable Long id){
        Optional<State> isState = stateService.getStateId(id);
        if(isState.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        stateService.delete(isState.get());
        return ResponseEntity.status(HttpStatus.OK).body("State deleted");
    }

}
