package com.tasks.taskswebbackend.controllers;

import com.tasks.taskswebbackend.dtos.DtoTag;
import com.tasks.taskswebbackend.models.Tag;
import com.tasks.taskswebbackend.services.TagService;
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
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/api")
public class TagController {

    //Revisar si hay otro metodo del repo para detectar si existe un registro con ese id que no devuelva el registro, solo un true o false
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/tag")
    public ResponseEntity<String> createState(@RequestBody Tag tag){
        System.out.println("Getting a post request to tag");

        //Managing headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "Application/json");

        System.out.println(tag);
        //Saving the tag by service
        Long stateId = tagService.save(tag);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stateId).toUri();
        //returning the response
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Tag created in: " + location);
    }

    @GetMapping("/tag")
    public ResponseEntity<Collection<Tag>> listState(){
        System.out.println("Getting a get request to tag");

        //Managing headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "Application/json");

        //Getting all tags by service
        Collection<Tag> tags = tagService.list();
        System.out.println(tags);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(tags);
    }

    @GetMapping("/tagsname")
    public ResponseEntity<Collection<DtoTag>> listDtoTags(){
        System.out.println("Getting a get request to tag");


        //Getting all tags by service
        Collection<Tag> tags = tagService.list();
        Collection<DtoTag> dtoTags = tags.stream().filter((tag)->{
            return tag.getState().getId() == 1L ;
        }).map((filterTag)->{
            return new DtoTag(filterTag.getName(),filterTag.getDescription());
        }).collect(Collectors.toList());
        System.out.println(tags);

        return ResponseEntity.status(HttpStatus.OK).body(dtoTags);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<Tag> listStateById(@PathVariable Long id){
        System.out.println("Getting by id");
        Optional<Tag> stateObtained = tagService.getTagId(id);
        return stateObtained.map(tag -> ResponseEntity.status(HttpStatus.OK).body(tag))
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());

    }



    @PutMapping("/tag/{id}")
    public ResponseEntity<String> updateState(@Valid @PathVariable Long id, @RequestBody Tag tag){
        Optional<Tag> isTag = tagService.getTagId(id);
        if(isTag.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        System.out.println(tag);
        tagService.save(tag);
        return ResponseEntity.status(HttpStatus.OK).body("Tag updated!");
    }

    @DeleteMapping("/tag/{id}")

    public ResponseEntity<String> deleteState(@PathVariable Long id){
        Optional<Tag> isTag = tagService.getTagId(id);
        if(isTag.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        tagService.delete(isTag.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tag deleted");
    }

}
