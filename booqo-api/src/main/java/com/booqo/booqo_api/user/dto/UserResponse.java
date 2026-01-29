package com.booqo.booqo_api.user.dto;

import com.booqo.booqo_api.user.entities.UserRole;

public record UserResponse(
        Long id,
        String username,
        String email,
        UserRole role,
        boolean hasCenter,
        String centerName
) {}