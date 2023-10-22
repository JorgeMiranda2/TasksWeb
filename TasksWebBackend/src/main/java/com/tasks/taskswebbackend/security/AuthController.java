package com.tasks.taskswebbackend.security;

import com.tasks.taskswebbackend.dtos.*;
import com.tasks.taskswebbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;


    public AuthController(AuthService authService, UserService userService, JWTService jwtService,UserDetailsService userDetailsService ){
        this.authService = authService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;

    }

    @PostMapping("/login")
    public ResponseEntity<DtoResponseLogin> login(@Valid @RequestBody DtoLogin dtoLogin){

        DtoResponseLogin response = authService.login(dtoLogin);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/validatetoken")
    public ResponseEntity<DtoValidateToken> validateToken(@Valid @RequestBody DtoToken dtoToken){

        DtoValidateToken dtoValidateToken = DtoValidateToken.builder().valid(false).build();

        try{
            final String username = jwtService.getUsernameFromToken(dtoToken.getToken());
            UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
            if(jwtService.isTokenValid(dtoToken.getToken(),userDetails)){
                dtoValidateToken.setValid(true);
            }
        }catch(Exception e){
            System.out.println("Token no valid: " + e);
        }




        return ResponseEntity.status(HttpStatus.OK).body(dtoValidateToken);
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
