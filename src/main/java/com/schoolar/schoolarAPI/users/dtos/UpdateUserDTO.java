package com.schoolar.schoolarAPI.users.dtos;

import com.schoolar.schoolarAPI.users.model.entities.User;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateUserDTO {
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 50 caracteres")
    private String name;
    @Size(min = 6, message = "A senha dever te pelo menos 6 caracteres")
    private String password;

    private String schoolGrade;
    private List<User.StudentSubjects> studentSubjects;
    private List<User.ProfessorSubjects> subjects;
    private List<User.Classrooms> classrooms;
}
