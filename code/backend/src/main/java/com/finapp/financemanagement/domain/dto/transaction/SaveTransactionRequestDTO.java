package com.finapp.financemanagement.domain.dto.transaction;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the request for saving a transaction.
 * This DTO contains information necessary to create a new transaction, including
 * the wallet ID, category ID, title, commentary, value, and date.
 */
public record SaveTransactionRequestDTO(
    @NotBlank String walletId,
    @NotBlank String categoryId,
    @NotBlank String title,
    @NotBlank String commentary,
    @NotNull Double value,
    @NotNull LocalDate date
) {
    
}
