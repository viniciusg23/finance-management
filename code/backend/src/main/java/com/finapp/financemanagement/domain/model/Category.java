package com.finapp.financemanagement.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.finapp.financemanagement.domain.enums.TransactionType;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a financial category.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CATEGORY")
public class Category {

    /**
     * The unique identifier of the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The icon associated with the category.
     */
    @ManyToOne
    @JoinColumn(name = "fk_icon_id", nullable = false, updatable = false)
    private Icon icon;

    /**
     * The profile associated with the category.
     */
    @ManyToOne
    @JoinColumn(name = "fk_profile_id", nullable = false, updatable = false)
    private Profile profile;

    /**
     * The name of the category.
     */
    @NotBlank
    @Column(nullable = false)
    private String name;

    /**
     * The type of transaction associated with the category.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    /**
     * The date and time when the category was created.
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically sets the creation date and time before persisting the category.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
