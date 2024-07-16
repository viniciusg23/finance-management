package com.finapp.financemanagement.domain.dto.user;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing the request for updating a user.
 * This DTO contains information necessary to update an existing user, including
 * the user's name, nickname, and email.
 */
public record UpdateUserRequestDTO(
    @NotBlank String name,
    @NotBlank String nickname,
    @NotBlank String email) {
    
}
