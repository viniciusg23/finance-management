package com.finapp.financemanagement.domain.dto.transaction;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

import com.finapp.financemanagement.domain.dto.category.UseCategoryResponseDTO;
import com.finapp.financemanagement.domain.dto.wallet.UseWalletResponseDTO;
import com.finapp.financemanagement.domain.enums.TransactionType;
import com.finapp.financemanagement.domain.model.Transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for retrieving a transaction.
 * This DTO contains information about a transaction including its ID, associated user ID,
 * profile ID, wallet ID, category ID, commentary, value, type, date, and creation timestamp.
 */
public record GetTransactionResponseDTO(
    @NotNull UUID id,
    @NotBlank String commentary,
    @NotNull Double value,
    @NotNull TransactionType type,
    @NotNull LocalDate date,
    @NotBlank String user,
    @NotNull UseWalletResponseDTO wallet,
    @NotNull UseCategoryResponseDTO category,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs a GetTransactionResponseDTO object based on the provided Transaction entity.
     *
     * @param transaction the Transaction entity to map to the DTO
     */
    public GetTransactionResponseDTO(Transaction transaction){
        this(
            transaction.getId(), 
            transaction.getCommentary(),
            transaction.getTransactionValue(),
            transaction.getType(),
            transaction.getDate(),
            transaction.getUser().getNickname(),
            new UseWalletResponseDTO(transaction.getWallet()), 
            new UseCategoryResponseDTO(transaction.getCategory()),
            Timestamp.valueOf(transaction.getCreatedAt())
        );
    }
}
