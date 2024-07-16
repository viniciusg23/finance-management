package com.finapp.financemanagement.domain.dto.wallet;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.dto.icon.UseIconResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.UseProfileResponseDTO;
import com.finapp.financemanagement.domain.model.Wallet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for saving a wallet.
 * This DTO contains information about the saved wallet including its ID, profile ID,
 * icon, name, description, color, whether it's a goal wallet, balance, and creation timestamp.
 */
public record SaveWalletResponseDTO(
    @NotNull UUID id,
    @NotNull UseProfileResponseDTO profile,
    @NotNull UseIconResponseDTO icon,
    @NotBlank String name,
    @NotBlank String description,
    @NotBlank String color,
    @NotNull Boolean goalWallet,
    @NotNull Double balance,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs a SaveWalletResponseDTO object based on the provided Wallet entity.
     *
     * @param wallet the Wallet entity to map to the DTO
     */
    public SaveWalletResponseDTO(Wallet wallet) {
        this(
            wallet.getId(),
            new UseProfileResponseDTO(wallet.getProfile()),
            new UseIconResponseDTO(wallet.getIcon()),
            wallet.getName(),
            wallet.getDescription(),
            wallet.getColor(),
            wallet.getGoalWallet(),
            wallet.getBalance(),
            Timestamp.valueOf(wallet.getCreatedAt())
        );
    }
}
