package com.tasks.taskswebbackend.services;

import com.tasks.taskswebbackend.interfaceservices.IProfileService;
import com.tasks.taskswebbackend.models.Profile;
import com.tasks.taskswebbackend.repositories.IProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService implements IProfileService {
    private  final IProfileRepo profileRepo;

    @Autowired
    public ProfileService(IProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    @Override
    public Collection<Profile> list(){
        Collection<Profile> profiles;
        profiles = profileRepo.findAll();
        return profiles;
    }
    @Override
    public Optional<Profile> getProfileId(Long id){
        return profileRepo.findById(id);
    }
    @Override
    public Long save(Profile profile){
        Profile savedProfile = profileRepo.save(profile);
        return savedProfile.getId();
    }
    @Override
    public void delete(Profile profile){
        profileRepo.delete(profile);
    }

}
