package com.schoolar.schoolarAPI.users.useCases.RegisterUser;

import com.schoolar.schoolarAPI.users.dtos.RegisterUserDTO;
import com.schoolar.schoolarAPI.users.exceptions.UserAlreadyExistsException;
import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import com.schoolar.schoolarAPI.users.model.entities.User;
import com.schoolar.schoolarAPI.utils.PasswordEncryption;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUser {

    private final IUsersRepository repository;

    public RegisterUser (IUsersRepository repository) {
        this.repository = repository;
    }

    public User execute(RegisterUserDTO data) throws UserAlreadyExistsException {
        Optional<User> user = repository.findByEnrollment(data.getEnrollment());
        if (user.isPresent()) throw new UserAlreadyExistsException("Já existe um usuário com essa matrícula");
        String hashedPassword = PasswordEncryption.hash(data.getPassword());
        data.setPassword(hashedPassword);
        return repository.register(data);
    }
}
