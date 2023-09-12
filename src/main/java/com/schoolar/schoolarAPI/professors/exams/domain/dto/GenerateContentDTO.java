package com.schoolar.schoolarAPI.professors.exams.domain.dto;

public record GenerateContentDTO(
        Integer questionsNumber,
        String theme,
        String subject
) {
}
