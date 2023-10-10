package com.tasks.taskswebbackend.interfaceservices;

import com.tasks.taskswebbackend.models.Tag;

import java.util.Collection;
import java.util.Optional;


public interface ITagService {
    public Collection<Tag> list();
    public Optional<Tag> getTagId(Long id);
    public Long save(Tag tag);
    public void delete(Tag tag);

}
