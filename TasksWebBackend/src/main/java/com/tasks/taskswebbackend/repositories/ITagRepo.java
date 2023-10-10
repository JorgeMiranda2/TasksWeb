package com.tasks.taskswebbackend.repositories;

import com.tasks.taskswebbackend.models.State;
import com.tasks.taskswebbackend.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRepo extends JpaRepository<Tag,Long> {
}
