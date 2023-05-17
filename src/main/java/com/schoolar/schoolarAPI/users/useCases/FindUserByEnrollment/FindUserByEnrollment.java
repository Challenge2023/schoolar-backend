package com.schoolar.schoolarAPI.users.useCases.FindUserByEnrollment;

import com.schoolar.schoolarAPI.users.exceptions.UserNotFoundException;
import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import com.schoolar.schoolarAPI.users.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserByEnrollment {

    private final IUsersRepository repository;

    public FindUserByEnrollment(IUsersRepository repository) {
        this.repository = repository;
    }

    public Optional<User> execute(String enrollment) {
        Optional<User> user = repository.findByEnrollment(enrollment);
        if (user.isEmpty()) throw new UserNotFoundException("Não há usuário com a matrícula informada");
        return user;
    }
}
