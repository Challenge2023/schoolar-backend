package com.schoolar.schoolarAPI.users.useCases.FindUsersBySchoolGrade;

import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import com.schoolar.schoolarAPI.users.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUserBySchoolGrade {
    private final IUsersRepository repository;

    public FindUserBySchoolGrade(IUsersRepository repository) {
        this.repository = repository;
    }

    public List<User> execute(String schoolGrade) {
        return repository.findUsersBySchoolGrade(schoolGrade);
    }
}
