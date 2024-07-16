package com.finapp.financemanagement.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an icon associated with a financial category.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ICON")
public class Icon {

    /**
     * The unique identifier of the icon.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The name of the icon.
     */
    @NotBlank
    @Column(nullable = false)
    private String name;

    /**
     * The unique identifier provided by a third-party service for the icon.
     */
    @NotBlank
    @Column(nullable = false, unique = true)
    private String thirdPartyId;

    /**
     * The date and time when the icon was created.
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically sets the creation date and time before persisting the icon.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
