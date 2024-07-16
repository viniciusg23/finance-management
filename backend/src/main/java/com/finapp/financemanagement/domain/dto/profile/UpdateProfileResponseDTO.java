package com.finapp.financemanagement.domain.dto.profile;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.model.Profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for updating a profile.
 * This DTO contains information about the updated profile including its ID,
 * associated user ID, name, and creation timestamp.
 */
public record UpdateProfileResponseDTO(
    @NotNull UUID id,
    @NotBlank String user,
    @NotBlank String name,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs an UpdateProfileResponseDTO object based on the provided Profile entity.
     *
     * @param profile the Profile entity to map to the DTO
     */
    public UpdateProfileResponseDTO(Profile profile){
        this(
            profile.getId(), 
            profile.getUser().getNickname(), 
            profile.getName(), 
            Timestamp.valueOf(profile.getCreatedAt())
        );
    }
}
