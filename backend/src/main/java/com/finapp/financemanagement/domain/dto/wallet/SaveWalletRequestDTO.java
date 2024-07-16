package com.finapp.financemanagement.domain.dto.wallet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the request for saving a wallet.
 * This DTO contains information necessary to create a new wallet, including
 * the profile ID, icon ID, name, description, color, and whether it's a goal wallet.
 */
public record SaveWalletRequestDTO(
    @NotBlank String profileId,
    @NotBlank String iconId,
    @NotBlank String name,
    @NotBlank String description,
    @NotBlank String color,
    @NotNull Boolean goalWallet
) {
    
}
