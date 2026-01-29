package com.booqo.booqo_api.client.controllers;

import com.booqo.booqo_api.client.dto.ClientRequest;
import com.booqo.booqo_api.client.dto.ClientResponse;
import com.booqo.booqo_api.client.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Endpoints para la gestión de clientes")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Crear Cliente")
    @PostMapping("/create")
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientRequest request, Authentication auth) {
        ClientResponse clientResponse = clientService.create(request, auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
    }

    @Operation(summary = "Listar mis Clientes")
    @GetMapping("/my-clients")
    public ResponseEntity<List<ClientResponse>> getMyClients(Authentication auth) {
        List<ClientResponse> clients = clientService.getMyClients(auth.getName());
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }
}
