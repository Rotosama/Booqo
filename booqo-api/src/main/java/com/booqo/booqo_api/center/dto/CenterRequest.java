package com.booqo.booqo_api.center.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CenterRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String name,

        @NotBlank(message = "El CIF es obligatorio")
        String cif,

        @NotBlank(message = "La dirección es obligatoria")
        String address,

        @NotBlank(message = "El teléfono es obligatorio")
        String phone,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Formato de email inválido")
        String email
) {}