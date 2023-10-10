package com.tasks.taskswebbackend.interfaceservices;

import com.tasks.taskswebbackend.models.Profile;


import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface IProfileService {
    public Collection<Profile> list();
    public Optional<Profile> getProfileId(Long id);
    public Long save(Profile profile);
    public void delete(Profile profile);

}
