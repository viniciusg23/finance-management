package com.finapp.financemanagement.domain.dto.transaction;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the request for updating a transaction.
 * This DTO contains information necessary to update an existing transaction, including
 * the transaction ID, category ID, title, commentary, value, and date.
 */
public record UpdateTransactionRequestDTO(
    @NotBlank String id,
    @NotBlank String categoryId,
    @NotBlank String title,
    @NotBlank String commentary,
    @NotNull Double value,
    @NotNull LocalDate date
) {
}
