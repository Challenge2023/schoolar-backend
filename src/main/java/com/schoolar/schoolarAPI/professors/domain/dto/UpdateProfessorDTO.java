package com.schoolar.schoolarAPI.professors.domain.dto;

import com.schoolar.schoolarAPI.professors.types.Classrooms;
import com.schoolar.schoolarAPI.professors.types.ProfessorSubjects;

import java.util.List;

public record UpdateProfessorDTO(
        String name,
        String email,
        String password,
        List<ProfessorSubjects> subjects,
        List<Classrooms> classrooms
) {
}
