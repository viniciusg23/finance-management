package com.finapp.financemanagement.domain.dto.profile;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing the response for retrieving a profile.
 * This DTO contains information about a profile including its ID, associated user ID,
 * name, and creation timestamp.
 */
import com.finapp.financemanagement.domain.model.Profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GetProfileResponseDTO(
    @NotNull UUID id,
    @NotBlank String user,
    @NotBlank String name,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs a GetProfileResponseDTO object based on the provided Profile entity.
     *
     * @param profile the Profile entity to map to the DTO
     */
    public GetProfileResponseDTO(Profile profile) {
        this(
            profile.getId(), 
            profile.getUser().getNickname(), 
            profile.getName(), 
            Timestamp.valueOf(profile.getCreatedAt())
        );
    }
}
