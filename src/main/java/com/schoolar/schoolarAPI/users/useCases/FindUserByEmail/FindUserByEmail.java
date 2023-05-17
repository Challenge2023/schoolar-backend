package com.schoolar.schoolarAPI.users.useCases.FindUserByEmail;

import com.schoolar.schoolarAPI.users.exceptions.UserNotFoundException;
import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import com.schoolar.schoolarAPI.users.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserByEmail {

    private final IUsersRepository repository;

    public FindUserByEmail(IUsersRepository repository) {
        this.repository = repository;
    }

    public Optional<User> execute(String email) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isEmpty()) throw new UserNotFoundException("Não há usuário com o email informado");
        return user;
    }
}
