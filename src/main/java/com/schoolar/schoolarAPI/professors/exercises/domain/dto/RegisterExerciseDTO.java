package com.schoolar.schoolarAPI.professors.exercises.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterExerciseDTO(
    @NotBlank
    String professor,
    @NotBlank
    String description
) {
}
