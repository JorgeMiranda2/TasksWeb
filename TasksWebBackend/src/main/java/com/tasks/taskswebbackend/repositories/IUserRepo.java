package com.tasks.taskswebbackend.repositories;

import com.tasks.taskswebbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);

    @Query(value = "SELECT id FROM user WHERE user_name=?1",nativeQuery=true)
    public Optional<Long> getUserIdFromUserName(String userName);
}
