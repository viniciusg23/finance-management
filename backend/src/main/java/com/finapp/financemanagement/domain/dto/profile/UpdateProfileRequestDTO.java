package com.finapp.financemanagement.domain.dto.profile;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing the request for updating a profile.
 * This DTO contains information necessary to update an existing profile,
 * including its ID and new name.
 */
public record UpdateProfileRequestDTO(
    @NotBlank String id,
    @NotBlank String name
) {
       
}
