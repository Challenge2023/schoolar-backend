package com.schoolar.schoolarAPI.students.domain.dto;

import com.schoolar.schoolarAPI.students.domain.schema.StudentSubjects;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RegisterStudentDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String schoolGrade,
        @NotBlank
        String enrollment,
        List<StudentSubjects> subjects
) {
}
