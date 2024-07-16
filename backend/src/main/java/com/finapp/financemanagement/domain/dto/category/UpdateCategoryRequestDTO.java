package com.finapp.financemanagement.domain.dto.category;

import com.finapp.financemanagement.domain.enums.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the request for updating a category.
 * This DTO contains information necessary to update an existing category,
 * including the category ID, new icon ID, name, and transaction type.
 */
public record UpdateCategoryRequestDTO(
    @NotBlank String id,
    @NotBlank String iconId,
    @NotBlank String name,
    @NotNull TransactionType type
) {
    
}
