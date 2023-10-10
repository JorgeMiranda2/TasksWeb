package com.tasks.taskswebbackend.services;

import com.tasks.taskswebbackend.interfaceservices.IStateService;
import com.tasks.taskswebbackend.models.State;
import com.tasks.taskswebbackend.repositories.IStateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StateService implements IStateService {
    private  final IStateRepo stateRepo;

    @Autowired
    public StateService(IStateRepo stateRepo) {
        this.stateRepo = stateRepo;
    }

    @Override
    public Collection<State> list(){
        Collection<State> states;
        states = stateRepo.findAll();
        return states;
    }
    @Override
    public Optional<State> getStateId(Long id){
        return stateRepo.findById(id);
    }
    @Override
    public Long save(State state){
        State savedState = stateRepo.save(state);
        return savedState.getId();
    }
    @Override
    public void delete(State state){
        stateRepo.delete(state);
    }

}
