package com.booqo.booqo_api.center.controllers;

import com.booqo.booqo_api.center.entities.Center;
import com.booqo.booqo_api.center.repositories.CenterRepository;
import com.booqo.booqo_api.center.dto.CenterRequest;
import com.booqo.booqo_api.center.dto.CenterResponse;
import com.booqo.booqo_api.user.entities.User;
import com.booqo.booqo_api.user.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/centers")
public class CenterController {
    private final CenterRepository centerRepository;
    private final UserRepository userRepository;
    public CenterController(CenterRepository centerRepository, UserRepository userRepository) {
        this.centerRepository = centerRepository;
        this.userRepository = userRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<CenterResponse> createCenter(@Valid @RequestBody CenterRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Center center = new Center();
        center.setName(request.name());
        center.setCif(request.cif());
        center.setAddress(request.address());
        center.setPhone(request.phone());
        center.setEmail(request.email());

        Center savedCenter = centerRepository.save(center);

        user.setCenter(savedCenter);
        userRepository.save(user);

        CenterResponse response = new CenterResponse(
                savedCenter.getId(),
                savedCenter.getName(),
                "Centro creado y vinculado exitosamente"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/my-center")
    public ResponseEntity<CenterResponse> getMyCenter(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Center center = user.getCenter();
        if (center == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(new CenterResponse(
                center.getId(),
                center.getName(),
                "Datos recuperados"
        ));
    }
}
