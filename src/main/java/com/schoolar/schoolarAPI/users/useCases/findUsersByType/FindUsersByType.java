package com.schoolar.schoolarAPI.users.useCases.findUsersByType;

import com.schoolar.schoolarAPI.users.dtos.FindByTypeDTO;
import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import com.schoolar.schoolarAPI.users.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUsersByType {
    private final IUsersRepository repository;

    public FindUsersByType(IUsersRepository repository) {
        this.repository = repository;
    }

    public List<User> execute(FindByTypeDTO type) {
        return repository.findByType(type);
    }
}
