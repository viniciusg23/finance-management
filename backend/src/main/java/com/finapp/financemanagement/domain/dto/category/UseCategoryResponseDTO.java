package com.finapp.financemanagement.domain.dto.category;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.dto.icon.GetIconResponseDTO;
import com.finapp.financemanagement.domain.enums.TransactionType;
import com.finapp.financemanagement.domain.model.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UseCategoryResponseDTO(
    @NotNull UUID id,
    @NotBlank String name,
    @NotNull GetIconResponseDTO icon,
    @NotNull TransactionType type,
    @NotNull Timestamp createdAt
) {
    public UseCategoryResponseDTO(Category category){
        this(
            category.getId(),
            category.getName(),
            new GetIconResponseDTO(category.getIcon()),
            category.getType(),
            Timestamp.valueOf(category.getCreatedAt())
        );
    }
}
