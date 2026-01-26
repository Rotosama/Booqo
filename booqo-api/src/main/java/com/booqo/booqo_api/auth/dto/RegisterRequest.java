package com.booqo.booqo_api.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos necesarios para registrar un nuevo trabajador")
public record RegisterRequest(
        @NotBlank
        @Schema(description = "Nombre de usuario del nuevo usuario", example = "Pepe")
        String username,

        @Email
        @NotBlank
        @Schema(description = "Correo electrónico del nuevo usuario", example = "example@xyz.com")
        String email,

        @NotBlank
        @Schema(description = "Contraseña del nuevo usuario", example = "strongPassword123")
        String password
) {}