package com.finapp.financemanagement.domain.dto.sharingInvite;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.dto.profile.UseProfileResponseDTO;
import com.finapp.financemanagement.domain.dto.user.UseUserResponseDTO;
import com.finapp.financemanagement.domain.enums.SharingInviteStatus;
import com.finapp.financemanagement.domain.model.SharingInvite;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for a user invite.
 * This DTO contains information about a sharing invite including its ID, status,
 * sender and receiver details, and associated profile information.
 */
public record GetUserInviteResponseDTO(
    @NotNull UUID id,
    @NotNull SharingInviteStatus status,
    @NotNull UseUserResponseDTO sender,
    @NotNull UseUserResponseDTO receiver,
    @NotNull UseProfileResponseDTO profile,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs a GetUserInviteResponseDTO object based on the provided SharingInvite entity.
     *
     * @param sharingInvite the SharingInvite entity to map to the DTO
     */
    public GetUserInviteResponseDTO(SharingInvite sharingInvite) {
        this(
            sharingInvite.getId(),
            sharingInvite.getStatus(),
            new UseUserResponseDTO(sharingInvite.getSender()),
            new UseUserResponseDTO(sharingInvite.getReceiver()),
            new UseProfileResponseDTO(sharingInvite.getProfile()),
            Timestamp.valueOf(sharingInvite.getCreatedAt())
        );
    }
}
