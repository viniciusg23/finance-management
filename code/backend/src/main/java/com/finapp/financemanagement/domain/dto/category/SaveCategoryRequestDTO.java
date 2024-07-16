package com.finapp.financemanagement.domain.dto.category;

import com.finapp.financemanagement.domain.enums.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the request for saving a category.
 * This DTO contains information necessary to create or update a category,
 * including the profile ID, icon ID, name, and transaction type.
 */
public record SaveCategoryRequestDTO(
    @NotBlank String profileId,
    @NotBlank String iconId,
    @NotBlank String name,
    @NotNull TransactionType type
) {
    
}
