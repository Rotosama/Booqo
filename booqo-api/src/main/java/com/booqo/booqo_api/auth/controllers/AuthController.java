package com.booqo.booqo_api.auth.controllers;


import com.booqo.booqo_api.auth.dto.AuthResponse;
import com.booqo.booqo_api.auth.dto.LoginRequest;
import com.booqo.booqo_api.auth.dto.RegisterRequest;
import com.booqo.booqo_api.auth.services.AuthService;
import com.booqo.booqo_api.config.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/auth")
@Tag(name="Autenticación", description="Endpoints para el registro y login de usuarios")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }
    @PostMapping("/register")
    @Operation(summary="Registro", description = "Crea un nuevo usuario y devuelve su token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de registro inválidos (ej. email mal formado)"),
            @ApiResponse(responseCode = "401", description = "Error de autenticación o usuario ya existente")
    })
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            String token = jwtService.generateToken(request.getEmail());
            return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(new AuthResponse(token));

        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary="Inicio Sesión", description = "Endpoint para iniciar sesión de un usuario")
@ApiResponses(value= {
        @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
        @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
})
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
