package com.finapp.financemanagement.domain.dto.icon;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing the request for updating an icon.
 * This DTO contains information necessary to update an existing icon,
 * including the icon ID, new name, and new third-party ID.
 */
public record UpdateIconRequestDTO(
    @NotBlank String id,
    @NotBlank String name,
    @NotBlank String thirdPartyId
) {
}
