package com.finapp.financemanagement.domain.dto.user;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing the request for saving a user.
 * This DTO contains information necessary to create a new user, including
 * the user's name, nickname, email, and password.
 */
public record SaveUserRequestDTO(
    @NotBlank String name, 
    @NotBlank String nickname,
    @NotBlank String email, 
    @NotBlank String password
) {    
}
