package com.finapp.financemanagement.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.finapp.financemanagement.domain.enums.SharingInviteStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an invitation for sharing financial data between users.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_SHARING_INVITE")
public class SharingInvite {

    /**
     * The unique identifier of the sharing invitation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    /**
     * The sender of the sharing invitation.
     */
    @ManyToOne
    @JoinColumn(name = "fk_sender_id", nullable = false, updatable = false)
    private User sender;

    /**
     * The receiver of the sharing invitation.
     */
    @ManyToOne
    @JoinColumn(name = "fk_receiver_id", nullable = false, updatable = false)
    private User receiver;

    /**
     * The profile associated with the sharing invitation.
     */
    @ManyToOne
    @JoinColumn(name = "fk_profile_id", nullable = false, updatable = false)
    private Profile profile;

    /**
     * The status of the sharing invitation.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SharingInviteStatus status;

    /**
     * The date and time when the sharing invitation was created.
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically sets the creation date and time before persisting the sharing invitation.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
