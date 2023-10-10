package com.tasks.taskswebbackend.repositories;

import com.tasks.taskswebbackend.models.Profile;
import com.tasks.taskswebbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IProfileRepo extends JpaRepository<Profile,Long> {
}
