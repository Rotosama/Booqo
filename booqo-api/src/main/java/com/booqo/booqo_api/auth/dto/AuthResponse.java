package com.booqo.booqo_api.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta que contiene el token de acceso")
public record AuthResponse(String token) { }