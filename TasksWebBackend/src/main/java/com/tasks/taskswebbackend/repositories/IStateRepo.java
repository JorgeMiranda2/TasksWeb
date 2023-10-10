package com.tasks.taskswebbackend.repositories;

import com.tasks.taskswebbackend.models.State;
import com.tasks.taskswebbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStateRepo extends JpaRepository<State,Long> {
}
