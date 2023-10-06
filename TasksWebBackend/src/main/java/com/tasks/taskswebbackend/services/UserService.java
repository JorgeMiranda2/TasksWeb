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
    private  IUserRepo repo;

    @Autowired
    public UserService(IUserRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<User> list(){
        return null;
    }
    @Override
    public Optional<User> listById(Long id){
        return null;
    }
    @Override
    public int save(User user){
        return 0;
    }
    @Override
    public void delete(Long Id){

    }

}
