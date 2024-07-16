package com.finapp.financemanagement.domain.dto.sharingInvite;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing the response for retrieving all user invites.
 * This DTO contains a list of individual user invite response DTOs.
 */
public record GetAllUserInvitesResponseDTO(
    List<GetUserInviteResponseDTO> invites
) { 
}
