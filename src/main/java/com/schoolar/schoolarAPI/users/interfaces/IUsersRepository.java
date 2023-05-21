package com.schoolar.schoolarAPI.users.interfaces;

import com.schoolar.schoolarAPI.users.dtos.FindByTypeDTO;
import com.schoolar.schoolarAPI.users.dtos.RegisterUserDTO;
import com.schoolar.schoolarAPI.users.dtos.UpdateUserDTO;
import com.schoolar.schoolarAPI.users.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUsersRepository {
    User register (RegisterUserDTO data);
    List<User> findAll();
    List<User> findByType(FindByTypeDTO type);
    Optional<User> findByEnrollment(String enrollment);
    List<User> findUsersBySchoolGrade(String schoolGrade);
    Optional<User> findByEmail(String email);
    User update(String enrollment, UpdateUserDTO data);
    void delete(String enrollment);
}
