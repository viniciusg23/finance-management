package com.finapp.financemanagement.domain.dto.profile;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing the request for saving a profile.
 * This DTO contains information necessary to create a new profile, including its name.
 */
public record SaveProfileRequestDTO(
    @NotBlank String name
) {
    
}
