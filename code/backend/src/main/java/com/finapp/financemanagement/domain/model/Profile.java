package com.finapp.financemanagement.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user profile containing financial data.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_PROFILE")
public class Profile {

    /**
     * The unique identifier of the profile.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The list of wallets associated with the profile.
     */
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Wallet> wallets;

    /**
     * The list of transactions associated with the profile.
     */
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    /**
     * The list of categories associated with the profile.
     */
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Category> categories;

    /**
     * The user associated with the profile.
     */
    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false, updatable = false)
    private User user;

    /**
     * The name of the profile.
     */
    @NotBlank
    @Column(nullable = false)
    private String name;

    /**
     * The date and time when the profile was created.
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically sets the creation date and time before persisting the profile.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
