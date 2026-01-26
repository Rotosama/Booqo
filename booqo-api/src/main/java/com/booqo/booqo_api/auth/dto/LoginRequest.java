package com.booqo.booqo_api.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos necesarios para iniciar sesión")
public record LoginRequest(
        @NotBlank
        @Schema(description = "Correo electrónico del usuario", example = "example@xyz.com")
        String email,

        @NotBlank
        @Schema(description = "Contraseña del usuario", example = "strongPassword123")
        String password
) {}