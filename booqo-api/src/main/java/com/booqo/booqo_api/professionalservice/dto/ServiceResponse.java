package com.booqo.booqo_api.professionalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ServiceResponse(
        @Schema(example = "1") Long id,
        @Schema(example = "Corte de pelo") String name,
        @Schema(example = "Servicio de peluquería básico") String description,
        @Schema(example = "25.50") Double price,
        @Schema(example = "30") Integer durationMinutes,
        @Schema(example = "true") boolean active
) {}