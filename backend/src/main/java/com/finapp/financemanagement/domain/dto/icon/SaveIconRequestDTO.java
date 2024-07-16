package com.finapp.financemanagement.domain.dto.icon;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing the request for saving an icon.
 * This DTO contains information necessary to create a new icon,
 * including the icon name and third-party ID.
 */
public record SaveIconRequestDTO(
    @NotBlank String name,
    @NotBlank String thirdPartyId
) {
}
