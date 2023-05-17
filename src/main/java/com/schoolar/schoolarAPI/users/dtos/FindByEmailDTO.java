package com.schoolar.schoolarAPI.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class FindByEmailDTO {
    @NotBlank(message = "O email deve ser preenchido")
    @Email(message = "Deve ser um email no formato correto")
    private String email;
}
