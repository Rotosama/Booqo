package com.booqo.booqo_api.booking.dto;

import java.time.LocalDateTime;

public record BookingRequest(
        LocalDateTime startTime,
        Long clientId,
        Long serviceId,
        String notes
) {}