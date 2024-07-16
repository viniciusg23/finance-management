package com.finapp.financemanagement.domain.dto.sharingInvite;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing the request for saving a sharing invite.
 * This DTO contains information necessary to create a new sharing invite, including
 * the receiver's nickname and the profile ID to be shared.
 */
public record SaveSharingInviteRequestDTO(
    @NotBlank String receiverNickname,
    @NotBlank String profileId
) {
}
