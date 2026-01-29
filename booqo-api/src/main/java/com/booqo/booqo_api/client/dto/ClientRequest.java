package com.booqo.booqo_api.client.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClientRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String firstName,

        @NotBlank(message = "El apellido es obligatorio")
        String lastName,

        String email,
        String phone,
        String dni,
        String address,
        LocalDate birthDate
) {
}