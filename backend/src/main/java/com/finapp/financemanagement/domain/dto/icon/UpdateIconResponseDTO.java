package com.finapp.financemanagement.domain.dto.icon;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.model.Icon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for updating an icon.
 * This DTO contains information about the updated icon including its ID, name,
 * third-party ID, and creation timestamp.
 */
public record UpdateIconResponseDTO(
    @NotNull UUID id,
    @NotBlank String name,
    @NotBlank String thirdPartyId,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs an UpdateIconResponseDTO object based on the provided Icon entity.
     *
     * @param icon the Icon entity to map to the DTO
     */
    public UpdateIconResponseDTO(Icon icon) {
        this(icon.getId(), icon.getName(), icon.getThirdPartyId(), Timestamp.valueOf(icon.getCreatedAt()));
    }
}
