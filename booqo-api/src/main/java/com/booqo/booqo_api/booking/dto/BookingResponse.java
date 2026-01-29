package com.booqo.booqo_api.booking.dto;

import com.booqo.booqo_api.booking.entities.BookingStatus;

import java.time.LocalDateTime;

public record BookingResponse(
        Long id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BookingStatus status,
        String clientName,
        String serviceName,
        String notes
) {}