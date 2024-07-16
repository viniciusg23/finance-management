package com.finapp.financemanagement.domain.dto.user;

import java.util.List;

import com.finapp.financemanagement.domain.dto.profile.GetProfileResponseDTO;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for retrieving all profiles associated with a user.
 * This DTO contains lists of profiles owned by the user and profiles shared with the user.
 */
public record GetAllUserProfilesResponseDTO(
    @NotNull List<GetProfileResponseDTO> ownm,
    @NotNull List<GetProfileResponseDTO> shared
) {

}
