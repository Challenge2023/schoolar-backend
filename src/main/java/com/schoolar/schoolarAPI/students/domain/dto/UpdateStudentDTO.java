package com.schoolar.schoolarAPI.students.domain.dto;

import com.schoolar.schoolarAPI.students.domain.schema.StudentSubjects;

import java.util.List;

public record UpdateStudentDTO(
        String name,
        String email,
        String password,
        String schoolGrade,
        List<StudentSubjects> subjects
) {
}
