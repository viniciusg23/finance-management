package com.finapp.financemanagement.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a sharing of financial data between users.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_SHARING")
public class Sharing {

    /**
     * The unique identifier of the sharing.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The owner of the sharing.
     */
    @ManyToOne
    @JoinColumn(name = "fk_owner_id", nullable = false)
    private User owner;

    /**
     * The guest of the sharing.
     */
    @ManyToOne
    @JoinColumn(name = "fk_guest_id", nullable = false)
    private User guest;

    /**
     * The profile associated with the sharing.
     */
    @ManyToOne
    @JoinColumn(name = "fk_profile_id", nullable = false)
    private Profile profile;

    /**
     * The date and time when the sharing was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically sets the creation date and time before persisting the sharing.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
