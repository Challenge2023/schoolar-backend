package com.schoolar.schoolarAPI.controllers;

import com.schoolar.schoolarAPI.users.dtos.FindByTypeDTO;
import com.schoolar.schoolarAPI.users.dtos.RegisterUserDTO;
import com.schoolar.schoolarAPI.users.dtos.UpdateUserDTO;
import com.schoolar.schoolarAPI.users.exceptions.UserAlreadyExistsException;
import com.schoolar.schoolarAPI.users.exceptions.UserNotFoundException;
import com.schoolar.schoolarAPI.users.model.entities.User;
import com.schoolar.schoolarAPI.users.useCases.DeleteUser.DeleteUser;
import com.schoolar.schoolarAPI.users.useCases.FindUserByEmail.FindUserByEmail;
import com.schoolar.schoolarAPI.users.useCases.FindUserByEnrollment.FindUserByEnrollment;
import com.schoolar.schoolarAPI.users.useCases.RegisterUser.RegisterUser;
import com.schoolar.schoolarAPI.users.useCases.UpdateUser.UpdateUser;
import com.schoolar.schoolarAPI.users.useCases.findAllUsers.FindAllUsers;
import com.schoolar.schoolarAPI.users.useCases.findUsersByType.FindUsersByType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final RegisterUser registerUser;
    private final FindAllUsers findAllUsers;
    private final FindUsersByType findUsersByType;
    private final FindUserByEnrollment findUserByEnrollment;
    private final FindUserByEmail findUserByEmail;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;

    public UsersController(RegisterUser registerUser, FindAllUsers findAllUsers, FindUsersByType findUsersByType, FindUserByEnrollment findUserByEnrollment, FindUserByEmail findUserByEmail, UpdateUser updateUser, DeleteUser deleteUser) {
        this.registerUser = registerUser;
        this.findAllUsers = findAllUsers;
        this.findUsersByType = findUsersByType;
        this.findUserByEnrollment = findUserByEnrollment;
        this.findUserByEmail = findUserByEmail;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register (@Valid @RequestBody RegisterUserDTO data) {
        try {
            User createdUser = registerUser.execute(data);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = findAllUsers.execute();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<User>> findUserByType(@Valid @PathVariable FindByTypeDTO type) throws UserNotFoundException{
        List<User> users = findUsersByType.execute(type);
        if (users.isEmpty()) throw new UserNotFoundException("Nenhum usuário encontrado");
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{enrollment}")
    public ResponseEntity<User> findUserByEnrollment(@PathVariable String enrollment) throws UserNotFoundException {
        User user = findUserByEnrollment.execute(enrollment).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email) throws UserNotFoundException {
        User user = findUserByEmail.execute(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{enrollment}")
    public ResponseEntity<User> updateUser(@PathVariable String enrollment, @Valid @RequestBody UpdateUserDTO updateUserDTO) throws UserNotFoundException {
        User updatedUser = updateUser.execute(enrollment, updateUserDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{enrollment}")
    public ResponseEntity<Void> deleteUser(@PathVariable String enrollment) throws UserNotFoundException {
        deleteUser.execute(enrollment);
        return ResponseEntity.noContent().build();
    }
}
