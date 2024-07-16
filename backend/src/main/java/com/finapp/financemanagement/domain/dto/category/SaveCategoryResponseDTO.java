package com.finapp.financemanagement.domain.dto.category;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.dto.icon.UseIconResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.UseProfileResponseDTO;
import com.finapp.financemanagement.domain.enums.TransactionType;
import com.finapp.financemanagement.domain.model.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for saving a category.
 * This DTO contains information about the saved category including its ID,
 * associated profile ID, icon details, name, transaction type, and creation timestamp.
 */
public record SaveCategoryResponseDTO(
    @NotNull UUID id,
    @NotBlank String name,
    @NotBlank TransactionType type,
    @NotBlank UseProfileResponseDTO profile,
    @NotBlank UseIconResponseDTO icon,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs a SaveCategoryResponseDTO object based on the provided Category entity.
     *
     * @param category the Category entity to map to the DTO
     */
    public SaveCategoryResponseDTO(Category category){
        this(
            category.getId(),
            category.getName(),
            category.getType(),
            new UseProfileResponseDTO(category.getProfile()),
            new UseIconResponseDTO(category.getIcon()),
            Timestamp.valueOf(category.getCreatedAt())
        );
    }
}
