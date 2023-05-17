package com.schoolar.schoolarAPI.users.dtos;

import com.schoolar.schoolarAPI.annotations.IsEnum;
import com.schoolar.schoolarAPI.constants.UserTypes;
import com.schoolar.schoolarAPI.users.model.entities.User;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class RegisterUserDTO {
    @NotBlank(message = "O nome deve ser preenchido")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 50 caracteres")
    private String name;

    @NotBlank(message = "O email deve ser preenchido")
    @Email(message = "Deve ser um email no formato correto")
    private String email;
    @NotBlank(message = "A senha dever ser preenchido")
    @Size(min = 6, message = "A senha dever te pelo menos 6 caracteres")
    private String password;

    @NotNull(message = "O tipo do usuaŕio deve ser preenchido")
    @IsEnum(enumClass = UserTypes.class, message = "O tipo deve ser um dos sequintes valores: PROFESSOR, STUDENT")
    private UserTypes type;

    @NotBlank(message = "O matricula deve ser preenchida")
    private String enrollment;

    @NotNull(message = "A data de nascimento deve ser preechida")
    @Past(message = "A data de nascimento deve ser uma data passada")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    private String schoolGrade;
    private List<User.StudentSubjects> studentSubjects;
    private List<User.ProfessorSubjects> subjects;
    private List<User.Classrooms> classrooms;

}
