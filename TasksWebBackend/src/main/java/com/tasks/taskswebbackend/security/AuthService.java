package com.tasks.taskswebbackend.security;


import com.tasks.taskswebbackend.models.Profile;
import com.tasks.taskswebbackend.models.User;
import com.tasks.taskswebbackend.repositories.IUserRepo;
import com.tasks.taskswebbackend.security.dtos.DtoLogin;
import com.tasks.taskswebbackend.security.dtos.DtoRegister;
import com.tasks.taskswebbackend.security.dtos.DtoResponseLogin;
import com.tasks.taskswebbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        String userName = user.getUsername();
        return DtoResponseLogin.builder().token(token).userName(userName).build();
    }

    public DtoResponseLogin register(DtoRegister dtoRegister){
        User user = new User();
        user.setProfile(new Profile());
        user.setUserName(dtoRegister.getUserName());
        user.setName(dtoRegister.getName());
        user.setLastName(dtoRegister.getLastName());
        user.setEmail(dtoRegister.getEmail());
        user.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));
        user.getProfile().setId(1L);
        userService.save(user);

        return DtoResponseLogin.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
