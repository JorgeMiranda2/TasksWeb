package com.tasks.taskswebbackend.services;

import com.tasks.taskswebbackend.interfaceservices.ITagService;
import com.tasks.taskswebbackend.models.Tag;
import com.tasks.taskswebbackend.repositories.ITagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TagService implements ITagService {
    private  final ITagRepo TagRepo;

    @Autowired
    public TagService(ITagRepo TagRepo) {
        this.TagRepo = TagRepo;
    }

    @Override
    public Collection<Tag> list(){
        Collection<Tag> tag;
        tag = TagRepo.findAll();
        return tag;
    }
    @Override
    public Optional<Tag> getTagId(Long id){
        return TagRepo.findById(id);
    }
    @Override
    public Long save(Tag tag){
        Tag savedTag = TagRepo.save(tag);
        return savedTag.getId();
    }
    @Override
    public void delete(Tag tag){
        TagRepo.delete(tag);
    }

}
