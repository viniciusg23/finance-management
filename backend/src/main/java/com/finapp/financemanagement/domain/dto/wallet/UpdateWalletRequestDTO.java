package com.finapp.financemanagement.domain.dto.wallet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the request for updating a wallet.
 * This DTO contains information necessary to update an existing wallet, including
 * its ID, icon ID, name, description, color, whether it's a goal wallet, and balance.
 */
public record UpdateWalletRequestDTO(
    @NotBlank String id,
    @NotBlank String iconId,
    @NotBlank String name,
    @NotBlank String description,
    @NotBlank String color,
    @NotNull Boolean goalWallet,
    @NotNull Double balance
) {
    
}
