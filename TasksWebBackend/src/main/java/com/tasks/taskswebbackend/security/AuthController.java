package com.tasks.taskswebbackend.security;

import com.tasks.taskswebbackend.security.dtos.DtoLogin;
import com.tasks.taskswebbackend.security.dtos.DtoRegister;
import com.tasks.taskswebbackend.security.dtos.DtoResponseLogin;
import com.tasks.taskswebbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService, UserService userService){
        this.authService = authService;

    }

    @PostMapping("/login")
    public ResponseEntity<DtoResponseLogin> login(@Valid @RequestBody DtoLogin dtoLogin){

        DtoResponseLogin response = authService.login(dtoLogin);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<DtoResponseLogin> register(@Valid @RequestBody DtoRegister dtoRegister){


        DtoResponseLogin response = authService.register(dtoRegister);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
