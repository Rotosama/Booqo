package com.booqo.booqo_api.professionalservice.services;

import com.booqo.booqo_api.professionalservice.ProfessionalServiceRepository;
import com.booqo.booqo_api.professionalservice.dto.ServiceRequest;
import com.booqo.booqo_api.professionalservice.dto.ServiceResponse;
import com.booqo.booqo_api.professionalservice.entities.ProfessionalService;
import com.booqo.booqo_api.user.User;
import com.booqo.booqo_api.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionalServiceService {

    private final ProfessionalServiceRepository repository;
    private final UserRepository userRepository;

    public ProfessionalServiceService(ProfessionalServiceRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ServiceResponse create(@Valid ServiceRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getCenter() == null) {
            throw new RuntimeException("El usuario no tiene un centro asociado");
        }

        ProfessionalService service = new ProfessionalService();
        service.setName(request.name());
        service.setDescription(request.description());
        service.setPrice(request.price());
        service.setDurationMinutes(request.durationMinutes());
        service.setCenter(user.getCenter());

        return mapToResponse(repository.save(service));
    }

    @Transactional(readOnly = true)
    public List<ServiceResponse> getServicesByCenter(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return repository.findByCenterId(user.getCenter().getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ServiceResponse mapToResponse(ProfessionalService service) {
        return new ServiceResponse(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getDurationMinutes(),
                service.isActive()
        );
    }
}