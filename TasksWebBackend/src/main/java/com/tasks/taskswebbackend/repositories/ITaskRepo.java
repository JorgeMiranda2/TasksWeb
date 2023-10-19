package com.tasks.taskswebbackend.repositories;

import com.tasks.taskswebbackend.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepo extends JpaRepository<Task,Long> {

@Query(value = "SELECT * FROM task WHERE user_id = ?1",nativeQuery = true)
     List<Task> getTasksByUserId(Long id);

}
