package com.schoolar.schoolarAPI.users.useCases.UpdateUser;

import com.schoolar.schoolarAPI.users.dtos.UpdateUserDTO;
import com.schoolar.schoolarAPI.users.exceptions.UserNotFoundException;
import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import com.schoolar.schoolarAPI.users.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUser {
    private final IUsersRepository repository;

    public UpdateUser (IUsersRepository repository) {
        this.repository = repository;
    }

    public User execute(String enrollment, UpdateUserDTO data) {
        Optional<User> user = repository.findByEnrollment(enrollment);
        if (user.isEmpty()) throw new UserNotFoundException("Não há usuário com a matrícula informada");
        return repository.update(enrollment, data);
    }
}
