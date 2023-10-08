package com.tasks.taskswebbackend.services;

import com.tasks.taskswebbackend.interfaceservices.IUserService;
import com.tasks.taskswebbackend.models.User;
import com.tasks.taskswebbackend.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private  final IUserRepo userRepo;

    @Autowired
    public UserService(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> list(){
        List<User> users;
        users = userRepo.findAll();
        return users;
    }
    @Override
    public Optional<User> getUserId(Long id){
        return userRepo.findById(id);
    }
    @Override
    public Long save(User user){
        User savedUser = userRepo.save(user);
        return savedUser.getId();
    }
    @Override
    public void delete(User user){
    userRepo.delete(user);
    }

}
