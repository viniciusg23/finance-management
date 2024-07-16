package com.finapp.financemanagement.domain.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

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
 * Represents a financial goal.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_GOAL")
public class Goal {

    /**
     * The unique identifier of the goal.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The wallet associated with the goal.
     */
    @OneToOne(mappedBy = "goal")
    private Wallet wallet;

    /**
     * The user associated with the goal.
     */
    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false, updatable = false)
    private User user;

    /**
     * The name of the goal.
     */
    @NotBlank
    @Column(nullable = false)
    private String name;

    /**
     * The description of the goal.
     */
    @NotNull
    @Column(nullable = false)
    private Double goalValue;

    /**
     * The final date of the goal.
     */
    @NotNull
    @Column(name = "final_date", nullable = false)
    private Date finalDate;

    /**
     * The date and time when the goal was created.
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically sets the creation date and time before persisting the goal.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }


}
