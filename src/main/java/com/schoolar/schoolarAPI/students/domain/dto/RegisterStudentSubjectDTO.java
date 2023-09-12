package com.schoolar.schoolarAPI.students.domain.dto;

public record RegisterStudentSubjectDTO(
        String code,
        Double avarageGrade,
        Integer absences
) {
}
