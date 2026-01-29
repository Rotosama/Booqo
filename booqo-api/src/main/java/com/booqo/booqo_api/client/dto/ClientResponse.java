package com.booqo.booqo_api.client.dto;

import java.time.LocalDate;

public record ClientResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String dni,
        String address,
        LocalDate birthDate,
        boolean active
) {
}