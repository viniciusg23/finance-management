package com.finapp.financemanagement.domain.dto.wallet;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.dto.icon.UseIconResponseDTO;
import com.finapp.financemanagement.domain.model.Wallet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UseWalletResponseDTO(
    @NotNull UUID id,
    @NotBlank String name,
    @NotBlank String description,
    @NotBlank String color,
    @NotNull Double balance,
    @NotNull UseIconResponseDTO icon,
    @NotNull Boolean goalWallet,
    @NotNull Timestamp createdAt
) {
    public UseWalletResponseDTO(Wallet wallet) {
        this(
            wallet.getId(),
            wallet.getName(),
            wallet.getDescription(),
            wallet.getColor(),
            wallet.getBalance(),
            new UseIconResponseDTO(wallet.getIcon()),
            wallet.getGoalWallet(),
            Timestamp.valueOf(wallet.getCreatedAt())
        );
    }
}