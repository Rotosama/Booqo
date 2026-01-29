package com.booqo.booqo_api.booking.services;

import com.booqo.booqo_api.booking.dto.BookingRequest;
import com.booqo.booqo_api.booking.dto.BookingResponse;
import com.booqo.booqo_api.booking.entities.Booking;
import com.booqo.booqo_api.booking.repositories.BookingRepository;
import com.booqo.booqo_api.client.entities.Client;
import com.booqo.booqo_api.client.repositories.ClientRepository;
import com.booqo.booqo_api.professionalservice.entities.ProfessionalService;
import com.booqo.booqo_api.professionalservice.repositories.ProfessionalServiceRepository;
import com.booqo.booqo_api.user.entities.User;
import com.booqo.booqo_api.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final ProfessionalServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, ClientRepository clientRepository,
                          ProfessionalServiceRepository serviceRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BookingResponse create(BookingRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Long centerId = user.getCenter().getId();
        // Validar que el cliente y el servicio pertenezcan al mismo centro que el usuario
        Client client = getValidatedClient(request.clientId(), centerId);
        ProfessionalService service = getValidatedService(request.serviceId(), centerId);

        Booking booking = new Booking();
        booking.setStartTime(request.startTime());
        // Cálculo automático del endTime usando la duración del servicio
        booking.setEndTime(request.startTime().plusMinutes(service.getDurationMinutes()));
        booking.setNotes(request.notes());
        booking.setClient(client);
        booking.setService(service);
        booking.setCenter(user.getCenter());

        Booking saved = bookingRepository.save(booking);
        return mapToResponse(saved);
    }

    public List<BookingResponse> getMyBookings(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return bookingRepository.findByCenterId(user.getCenter().getId())
                .stream().map(this::mapToResponse).toList();
    }

    private BookingResponse mapToResponse(Booking b) {
        return new BookingResponse(
                b.getId(),
                b.getStartTime(),
                b.getEndTime(),
                b.getStatus(),
                b.getClient().getFirstName() + " " + b.getClient().getLastName(),
                b.getService().getName(),
                b.getNotes()
        );
    }

    private Client getValidatedClient(Long clientId, Long centerId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        if (!client.getCenter().getId().equals(centerId)) {
            throw new RuntimeException("El cliente no pertenece a tu centro");
        }
        return client;
    }

    private ProfessionalService getValidatedService(Long serviceId, Long centerId) {
        ProfessionalService service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        if (!service.getCenter().getId().equals(centerId)) {
            throw new RuntimeException("El servicio no pertenece a tu centro");
        }
        return service;
    }
}
