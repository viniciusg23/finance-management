package com.finapp.financemanagement.domain.dto.category;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.dto.icon.UseIconResponseDTO;
import com.finapp.financemanagement.domain.enums.TransactionType;
import com.finapp.financemanagement.domain.model.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for retrieving a category.
 * This DTO contains information about a category including its ID, name, icon details,
 * transaction type, and creation timestamp.
 */
public record GetCategoryResponseDTO(
    @NotNull UUID id,
    @NotBlank String name,
    @NotNull UseIconResponseDTO icon,
    @NotNull TransactionType type,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs a GetCategoryResponseDTO object based on the provided Category entity.
     *
     * @param category the Category entity to map to the DTO
     */
    public GetCategoryResponseDTO(Category category){
        this(
            category.getId(),
            category.getName(),
            new UseIconResponseDTO(category.getIcon()),
            category.getType(),
            Timestamp.valueOf(category.getCreatedAt())
        );
    }
}
