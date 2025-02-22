package com.finapp.financemanagement.domain.dto.icon;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.model.Icon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UseIconResponseDTO(
    @NotNull UUID id,
    @NotBlank String name,
    @NotBlank String thirdPartyId,
    @NotNull Timestamp createdAt
) {
    public UseIconResponseDTO(Icon icon) {
        this(icon.getId(), icon.getName(), icon.getThirdPartyId(), Timestamp.valueOf(icon.getCreatedAt()));
    }
}
