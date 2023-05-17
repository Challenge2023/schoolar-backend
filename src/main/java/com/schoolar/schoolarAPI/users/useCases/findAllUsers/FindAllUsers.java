package com.schoolar.schoolarAPI.users.useCases.findAllUsers;

import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import com.schoolar.schoolarAPI.users.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUsers {

    private final IUsersRepository repository;

    public FindAllUsers (IUsersRepository repository) {
        this.repository = repository;
    }

    public List<User> execute() {
        return this.repository.findAll();
    }
}
