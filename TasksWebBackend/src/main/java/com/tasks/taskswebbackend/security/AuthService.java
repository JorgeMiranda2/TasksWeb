package com.tasks.taskswebbackend.security;


import com.tasks.taskswebbackend.models.Profile;
import com.tasks.taskswebbackend.models.User;
import com.tasks.taskswebbackend.repositories.IUserRepo;
import com.tasks.taskswebbackend.dtos.DtoLogin;
import com.tasks.taskswebbackend.dtos.DtoRegister;
import com.tasks.taskswebbackend.dtos.DtoResponseLogin;
import com.tasks.taskswebbackend.services.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthService {



    @Autowired
    private  UserService userService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserRepo userRepo;


    public DtoResponseLogin login(DtoLogin dtoLogin){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoLogin.getUserName(),dtoLogin.getPassword()));
        UserDetails user = userRepo.findByUserName(dtoLogin.getUserName()).orElseThrow();
        String token = jwtService.getToken(user);
        User user2  = userRepo.findByUserName(dtoLogin.getUserName()).orElseThrow();
        System.out.println("user2: " + user2);
        String userName = user.getUsername();
        System.out.println(user.getAuthorities());
        return DtoResponseLogin.builder().token(token).userName(userName).build();
    }

    public Collection getRole(DtoLogin dtoLogin){
        UserDetails user = userRepo.findByUserName(dtoLogin.getUserName()).orElseThrow();
        return user.getAuthorities();
    }

    public DtoResponseLogin register(DtoRegister dtoRegister) {
        return register(dtoRegister, 1L);
    }
    public DtoResponseLogin register(DtoRegister dtoRegister, Long roleId){

        User user = new User();
        user.setProfile(new Profile());
        user.setUserName(dtoRegister.getUserName());
        user.setName(dtoRegister.getName());
        user.setLastName(dtoRegister.getLastName());
        user.setEmail(dtoRegister.getEmail());
        user.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));
        user.getProfile().setId(roleId);
        userService.save(user);

        return DtoResponseLogin.builder()
                .token(jwtService.getToken(user))
                .userName(dtoRegister.getUserName())
                .build();
    }

    public DtoResponseLogin registerAdmin(DtoRegister dtoRegister) {
        return register(dtoRegister,2L);
    }

    public Claims getClaims(String token){
        return jwtService.getAllClaims(token);
    }

    public boolean validateToken(String token){
        return jwtService.isTokenValid(token, null);
    }
}
