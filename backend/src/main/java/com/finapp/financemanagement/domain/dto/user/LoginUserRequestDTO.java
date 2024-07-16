package com.finapp.financemanagement.domain.dto.user;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing the request for user login.
 * This DTO contains the user's email and password for authentication.
 */
public record LoginUserRequestDTO(@NotBlank String email, @NotBlank String password) {
}
