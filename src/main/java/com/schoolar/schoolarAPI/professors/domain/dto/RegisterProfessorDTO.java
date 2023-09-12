package com.schoolar.schoolarAPI.professors.domain.dto;

import com.schoolar.schoolarAPI.professors.types.Classrooms;
import com.schoolar.schoolarAPI.professors.types.ProfessorSubjects;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RegisterProfessorDTO(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotBlank
        String enrollment,
        @NotBlank
        List<ProfessorSubjects> subjects,
        @NotBlank
        List<Classrooms> classrooms
) {
}
