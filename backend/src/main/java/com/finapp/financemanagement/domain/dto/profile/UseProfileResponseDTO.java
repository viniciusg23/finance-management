package com.finapp.financemanagement.domain.dto.profile;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.model.Profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UseProfileResponseDTO(
    @NotNull UUID id,
    @NotBlank String creator,
    @NotBlank String name,
    @NotNull Timestamp createdAt
) {
    public UseProfileResponseDTO(Profile profile) {
        this(
            profile.getId(), 
            profile.getUser().getNickname(), 
            profile.getName(), 
            Timestamp.valueOf(profile.getCreatedAt())
        );
    }
}
