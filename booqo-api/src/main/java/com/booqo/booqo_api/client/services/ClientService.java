package com.booqo.booqo_api.client.services;

import com.booqo.booqo_api.client.dto.ClientRequest;
import com.booqo.booqo_api.client.dto.ClientResponse;
import com.booqo.booqo_api.client.entities.Client;
import com.booqo.booqo_api.client.repositories.ClientRepository;
import com.booqo.booqo_api.user.entities.User;
import com.booqo.booqo_api.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientService(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ClientResponse create(ClientRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Client client = new Client();
        client.setFirstName(request.firstName());
        client.setLastName(request.lastName());
        client.setEmail(request.email());
        client.setPhone(request.phone());
        client.setDni(request.dni());
        client.setAddress(request.address());
        client.setBirthDate(request.birthDate());
        client.setCenter(user.getCenter());

        return mapToResponse(clientRepository.save(client));
    }
    public List<ClientResponse> getMyClients(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return clientRepository.findByCenterId(user.getCenter().getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ClientResponse mapToResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhone(),
                client.getDni(),
                client.getAddress(),
                client.getBirthDate(),
                client.isActive()
        );
    }
}
