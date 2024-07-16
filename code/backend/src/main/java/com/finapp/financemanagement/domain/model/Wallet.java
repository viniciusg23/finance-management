package com.finapp.financemanagement.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a wallet used for managing financial assets.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_WALLET")
public class Wallet {

    /**
     * The unique identifier of the wallet.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The icon associated with the wallet.
     */
    @ManyToOne
    @JoinColumn(name = "fk_icon_id", nullable = false)
    private Icon icon;

    /**
     * The goal associated with the wallet.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_goal_id", nullable = true)
    private Goal goal;

    /**
     * The user who owns the wallet.
     */
    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false, updatable = false)
    private User user;

    /**
     * The profile associated with the wallet.
     */
    @ManyToOne
    @JoinColumn(name = "fk_profile_id", nullable = false, updatable = false)
    private Profile profile;

    /**
     * The name of the wallet.
     */
    @NotBlank
    @Column(nullable = false)
    private String name;

    /**
     * The description of the wallet.
     */
    @Column(nullable = true)
    private String description;

    /**
     * The color associated with the wallet.
     */
    @NotBlank
    @Column(nullable = false)
    private String color;

    /**
     * The balance of the wallet.
     */
    @NotNull
    @Column(nullable = false)
    private Double balance;

    /**
     * Indicates if the wallet is associated with a goal.
     */
    @NotNull
    @Column(nullable = false, name = "goal_wallet")
    private Boolean goalWallet;

    /**
     * The date and time when the wallet was created.
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically sets the creation date and time before persisting the wallet.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
