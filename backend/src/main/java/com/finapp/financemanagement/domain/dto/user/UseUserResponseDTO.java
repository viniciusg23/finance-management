package com.finapp.financemanagement.domain.dto.user;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UseUserResponseDTO(
    @NotNull UUID id,
    @NotBlank String name,
    @NotBlank String nickname,
    @NotNull Timestamp createdAt
) {
    public UseUserResponseDTO(User user) {
        this(
            user.getId(), 
            user.getName(), 
            user.getNickname(), 
            Timestamp.valueOf(user.getCreatedAt())
        );
    }
}
