package com.tasks.taskswebbackend.controllers;

import com.tasks.taskswebbackend.models.Profile;
import com.tasks.taskswebbackend.models.Profile;
import com.tasks.taskswebbackend.services.ProfileService;
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
public class ProfileController {

    //Revisar si hay otro metodo del repo para detectar si existe un registro con ese id que no devuelva el registro, solo un true o false
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profile")
    public ResponseEntity<String> createProfile(@RequestBody Profile profile){
        System.out.println("Getting a post request to profile");

        //Managing headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "Application/json");

        System.out.println(profile);
        //Saving the profile by service
        Long profileId = profileService.save(profile);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(profileId).toUri();
        //returning the response
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Profile created in: " + location);
    }

    @GetMapping("/profile")
    public ResponseEntity<Collection<Profile>> listProfile(){
        System.out.println("Getting a get request to profile");

        //Managing headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "Application/json");

        //Getting all Profiles by service
        Collection<Profile> profiles = profileService.list();
        System.out.println(profiles);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(profiles);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Profile> listProfileById(@PathVariable Long id){
        System.out.println("Getting by id");
        Optional<Profile> profileObtained = profileService.getProfileId(id);
        return profileObtained.map(profile -> ResponseEntity.status(HttpStatus.OK).body(profile))
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());

    }



    @PutMapping("/profile/{id}")
    public ResponseEntity<String> updateProfile(@Valid @PathVariable Long id, @RequestBody Profile profile){
        Optional<Profile> isProfile = profileService.getProfileId(id);
        if(isProfile.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        System.out.println(profile);
        profileService.save(profile);
        return ResponseEntity.status(HttpStatus.OK).body("Profile updated!");
    }

    @DeleteMapping("/profile/{id}")

    public ResponseEntity<String> deleteProfile(@PathVariable Long id){
        Optional<Profile> isProfile = profileService.getProfileId(id);
        if(isProfile.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        profileService.delete(isProfile.get());
        return ResponseEntity.status(HttpStatus.OK).body("Profile deleted");
    }

}
