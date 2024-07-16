package com.finapp.financemanagement.domain.dto.wallet;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.dto.icon.UseIconResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.UseProfileResponseDTO;
import com.finapp.financemanagement.domain.model.Wallet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for retrieving a wallet.
 * This DTO contains information about the wallet including its ID, profile ID, name,
 * description, color, balance, icon, whether it's a goal wallet, and creation timestamp.
 */
public record GetWalletResponseDTO(
    @NotNull UUID id,
    @NotNull UseProfileResponseDTO profile,
    @NotBlank String name,
    @NotBlank String description,
    @NotBlank String color,
    @NotNull Double balance,
    @NotNull UseIconResponseDTO icon,
    @NotNull Boolean goalWallet,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs a GetWalletResponseDTO object based on the provided Wallet entity.
     *
     * @param wallet the Wallet entity to map to the DTO
     */
    public GetWalletResponseDTO(Wallet wallet) {
        this(
            wallet.getId(),
            new UseProfileResponseDTO(wallet.getProfile()),
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
