package com.tasks.taskswebbackend.security;

import com.tasks.taskswebbackend.dtos.DtoLogin;
import com.tasks.taskswebbackend.dtos.DtoRegister;
import com.tasks.taskswebbackend.dtos.DtoResponseLogin;
import com.tasks.taskswebbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

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

    @PostMapping("/registeradmin")
    public ResponseEntity<DtoResponseLogin> registerAdmin(@Valid @RequestBody DtoRegister dtoRegister){
        DtoResponseLogin response = authService.registerAdmin(dtoRegister);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping ("/showroles")
    public ResponseEntity<Collection> showRoles(@Valid @RequestBody DtoLogin dtoLogin){

        Collection autorities = authService.getRole(dtoLogin);
        return ResponseEntity.status(HttpStatus.OK).body(autorities);
    }

}
