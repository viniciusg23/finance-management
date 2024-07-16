package com.finapp.financemanagement.domain.dto.user;

import java.sql.Timestamp;
import java.util.UUID;

import com.finapp.financemanagement.domain.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing the response for saving a user.
 * This DTO contains information about the saved user including its ID, name, nickname,
 * email, and creation timestamp.
 */
public record SaveUserResponseDTO(
    @NotBlank UUID id,
    @NotBlank String name,
    @NotBlank String nickname,
    @NotBlank String email,
    @NotNull Timestamp createdAt
) {
    /**
     * Constructs a SaveUserResponseDTO object based on the provided User entity.
     *
     * @param user the User entity to map to the DTO
     */
    public SaveUserResponseDTO(User user) {
        this(
            user.getId(), 
            user.getName(), 
            user.getNickname(), 
            user.getEmail(), 
            Timestamp.valueOf(user.getCreatedAt())
        );
    }      
}
