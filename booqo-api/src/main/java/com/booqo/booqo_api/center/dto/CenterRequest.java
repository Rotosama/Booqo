package com.booqo.booqo_api.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos para registrar un nuevo centro")
public class CenterRequest {

    @Schema(example = "Centro Médico Booqo Norte")
    @NotBlank(message = "El nombre del centro es obligatorio")
    private String name;

    @Schema(example = "B12345678")
    @NotBlank(message = "El CIF es obligatorio")
    private String cif;

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCif() { return cif; }
    public void setCif(String cif) { this.cif = cif; }
}