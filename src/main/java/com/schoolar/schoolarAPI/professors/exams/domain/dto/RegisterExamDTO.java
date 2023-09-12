package com.schoolar.schoolarAPI.professors.exams.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterExamDTO(
        @NotBlank
        String student,
        @NotBlank
        String professor,
        @NotBlank
        String questions,
        @NotBlank
        Integer result
) {
}
