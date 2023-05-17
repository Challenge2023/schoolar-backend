package com.schoolar.schoolarAPI.users.useCases.DeleteUser;

import com.schoolar.schoolarAPI.users.exceptions.UserNotFoundException;
import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import com.schoolar.schoolarAPI.users.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteUser {

    private final IUsersRepository repository;

    public DeleteUser(IUsersRepository repository) {
        this.repository = repository;
    }

    public void execute(String enrollment) {
        Optional<User> user = repository.findByEnrollment(enrollment);
        if(user.isEmpty()) throw new UserNotFoundException("Não há usuário com a matrícula informada");
        repository.delete(enrollment);
    }
}
