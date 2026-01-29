package com.booqo.booqo_api.booking.controllers;

import com.booqo.booqo_api.booking.dto.BookingRequest;
import com.booqo.booqo_api.booking.dto.BookingResponse;
import com.booqo.booqo_api.booking.services.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Reservas (Bookings)")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BookingRequest request, Authentication auth) {
        try {
            BookingResponse response = bookingService.create(request, auth.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<?> getMyBookings(Authentication auth) {
        try {
            return ResponseEntity.ok(bookingService.getMyBookings(auth.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al obtener las reservas"));
        }
    }
}
