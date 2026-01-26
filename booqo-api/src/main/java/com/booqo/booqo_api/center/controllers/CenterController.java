package com.booqo.booqo_api.center.controllers;

import com.booqo.booqo_api.center.Center;
import com.booqo.booqo_api.center.CenterRepository;
import com.booqo.booqo_api.center.dto.CenterRequest;
import com.booqo.booqo_api.center.dto.CenterResponse;
import com.booqo.booqo_api.user.User;
import com.booqo.booqo_api.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/centers")
public class CenterController {
    private final CenterRepository centerRepository;
    private final UserRepository userRepository;
    public CenterController(CenterRepository centerRepository, UserRepository userRepository) {
        this.centerRepository = centerRepository;
        this.userRepository = userRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<CenterResponse> createCenter(@Valid @RequestBody CenterRequest request, Authentication authentication) {
        // 1. Obtener el email del usuario desde el Token
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Crear y guardar el nuevo Centro
        Center center = new Center();
        center.setName(request.name());
        center.setCif(request.cif());
        center.setAddress(request.address());
        center.setPhone(request.phone());
        center.setEmail(request.email());

        Center savedCenter = centerRepository.save(center);

        // 3. Vincular el usuario al centro
        user.setCenter(savedCenter);
        userRepository.save(user);

        CenterResponse response = new CenterResponse(
                savedCenter.getId(),
                savedCenter.getName(),
                "Centro creado y vinculado exitosamente"
        );

        return ResponseEntity.status((201)).body(response);
    }
}
