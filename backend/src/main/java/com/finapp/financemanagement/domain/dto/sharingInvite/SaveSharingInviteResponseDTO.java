package com.finapp.financemanagement.domain.dto.sharingInvite;

import java.util.UUID;

import com.finapp.financemanagement.domain.dto.profile.UseProfileResponseDTO;
import com.finapp.financemanagement.domain.dto.user.UseUserResponseDTO;
import com.finapp.financemanagement.domain.model.SharingInvite;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for saving a sharing invite.
 * This DTO contains information about the saved sharing invite including its ID,
 * sender ID, receiver ID, and the profile ID.
 */
public record SaveSharingInviteResponseDTO(
    @NotNull UUID id,
    @NotNull UseUserResponseDTO sender,
    @NotNull UseUserResponseDTO receiver,
    @NotNull UseProfileResponseDTO profile
) {
    /**
     * Constructs a SaveSharingInviteResponseDTO object based on the provided SharingInvite entity.
     *
     * @param sharingInvite the SharingInvite entity to map to the DTO
     */
    public SaveSharingInviteResponseDTO(SharingInvite sharingInvite) {
        this(
            sharingInvite.getId(),
            new UseUserResponseDTO(sharingInvite.getSender()),
            new UseUserResponseDTO(sharingInvite.getReceiver()),
            new UseProfileResponseDTO(sharingInvite.getProfile())
        );
    }
}
