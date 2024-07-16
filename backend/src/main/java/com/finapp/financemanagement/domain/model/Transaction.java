package com.finapp.financemanagement.domain.model;

import java.time.LocalDate;
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
 * Represents a financial transaction.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_TRANSACTION")
public class Transaction {

    /**
     * The unique identifier of the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The wallet associated with the transaction.
     */
    @ManyToOne
    @JoinColumn(name = "fk_wallet_id", nullable = false, updatable = false)
    private Wallet wallet;

    /**
     * The category associated with the transaction.
     */
    @ManyToOne
    @JoinColumn(name = "fk_category_id", nullable = false, updatable = false)
    private Category category;

    /**
     * The profile associated with the transaction.
     */
    @ManyToOne
    @JoinColumn(name = "fk_profile_id", nullable = false, updatable = false)
    private Profile profile;

    /**
     * The user associated with the transaction.
     */
    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false, updatable = false)
    private User user;

    /**
     * The title of the transaction.
     */
    @NotBlank
    @Column(nullable = false)
    private String title;

    /**
     * Additional commentary for the transaction.
     */
    @Column(nullable = true)
    private String commentary;

    /**
     * The value of the transaction.
     */
    @NotNull
    @Column(nullable = false)
    private Double transactionValue;

    /**
     * The date of the transaction.
     */
    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    /**
     * The type of transaction.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    /**
     * The date and time when the transaction was created.
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically sets the creation date and time before persisting the transaction.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
