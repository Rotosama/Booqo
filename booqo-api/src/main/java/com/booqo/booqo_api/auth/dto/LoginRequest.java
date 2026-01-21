package com.booqo.booqo_api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos necesarios para iniciar sesión")
public class LoginRequest {
    @NotBlank
    @Schema(description = "Correo electrónico del usuario",
            example = "example@xyz.com")
    private String email;
    @Schema(description = "Contraseña del usuario",
            example = "strongPassword123")
    @NotBlank
    private String password;

    // Getters y setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
