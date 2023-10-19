package com.tasks.taskswebbackend.interfaceservices;

import com.tasks.taskswebbackend.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



public interface IUserService {
    public List<User> list();
    public Optional<User> getUserId(Long id);
    public Long save(User user);
    public void delete(User user);
    public Optional<Long> getUserIdFromUserName(String userName);

}
