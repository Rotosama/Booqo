package com.booqo.booqo_api.professionalservice.controllers;

import com.booqo.booqo_api.professionalservice.dto.ServiceRequest;
import com.booqo.booqo_api.professionalservice.dto.ServiceResponse;
import com.booqo.booqo_api.professionalservice.services.ProfessionalServiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="Servicios", description="Endpoints para creación y obtención de servicios profesionales")
@RestController
@RequestMapping("/api/services")
public class ProfessionalServiceController {

    private final ProfessionalServiceService serviceLogic;

    public ProfessionalServiceController(ProfessionalServiceService serviceLogic) {
        this.serviceLogic = serviceLogic;
    }
    @Operation(summary="Crear Servicio", description="Crea un nuevo servicio profesional asociado al centro del usuario autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servicio creado con éxito"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Token inválido o usuario sin centro", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody ServiceRequest request, Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceLogic.create(request, auth.getName()));
    }
@Operation(summary="Obtener Mis Servicios", description="Obtiene todos los servicios profesionales asociados al centro del usuario autenticado")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de servicios obtenida con éxito"),
        @ApiResponse(responseCode = "401", description = "No autorizado - Token inválido o usuario sin centro", content = @Content)
})
    @GetMapping("/my-services")
    public ResponseEntity<List<ServiceResponse>> getMyServices(Authentication auth) {
        try {
            List<ServiceResponse> services = serviceLogic.getServicesByCenter(auth.getName());
            return ResponseEntity.status(HttpStatus.OK).body(services);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}