package com.finapp.financemanagement.domain.dto.user;

import java.util.List;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for retrieving all users.
 * This DTO contains a list of user response DTOs.
 */
public record GetAllUsersResponseDTO(
    @NotNull List<GetUserResponseDTO> users
) {
    
}
