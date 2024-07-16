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
 * Data Transfer Object (DTO) representing the response for updating a category.
 * This DTO contains information about the updated category including its ID,
 * associated profile ID, icon details, name, transaction type, and creation timestamp.
 */
public record UpdateCategoryResponseDTO(
    @NotNull UUID id,
    @NotBlank UseProfileResponseDTO profile,
    @NotBlank UseIconResponseDTO icon,
    @NotBlank String name,
    @NotBlank TransactionType type,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs an UpdateCategoryResponseDTO object based on the provided Category entity.
     *
     * @param category the Category entity to map to the DTO
     */
    public UpdateCategoryResponseDTO(Category category){
        this(
            category.getId(),
            new UseProfileResponseDTO(category.getProfile()),
            new UseIconResponseDTO(category.getIcon()),
            category.getName(),
            category.getType(),
            Timestamp.valueOf(category.getCreatedAt())
        );
    }
}
