package com.booqo.booqo_api.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos necesarios para registrar un nuevo trabajador")
public class RegisterRequest {

    @NotBlank
    @Schema(description = "Nombre de usuario del nuevo usuario",
            example = "Pepe")
    private String username;

    @Email
    @NotBlank
    @Schema(description = "Correo electrónico del nuevo usuario",
            example = "example@xyz.com")
    private String email;

    @NotBlank
    @Schema(description = "Contraseña del nuevo usuario",
            example = "strongPassword123")
    private String password;

    // Getters y setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
