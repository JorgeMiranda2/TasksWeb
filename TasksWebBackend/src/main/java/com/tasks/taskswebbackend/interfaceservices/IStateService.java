package com.tasks.taskswebbackend.interfaceservices;

import com.tasks.taskswebbackend.models.State;

import java.util.Collection;
import java.util.Optional;


public interface IStateService {
    public Collection<State> list();
    public Optional<State> getStateId(Long id);
    public Long save(State state);
    public void delete(State state);

}
