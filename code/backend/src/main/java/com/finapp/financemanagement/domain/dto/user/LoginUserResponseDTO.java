package com.finapp.financemanagement.domain.dto.user;

import java.util.UUID;

import com.finapp.financemanagement.domain.model.User;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing the response for user login.
 * This DTO contains the authentication token along with the user's name and nickname.
 */
public record LoginUserResponseDTO(
    @NotBlank UUID id,
    @NotBlank String name,
    @NotBlank String nickname,
    @NotBlank String token
) {
    /**
     * Constructs a LoginUserResponseDTO object with the provided authentication token and user details.
     *
     * @param token the authentication token
     * @param user  the User entity to extract name and nickname from
     */
    public LoginUserResponseDTO(String token, User user) {
        this(user.getId(), user.getName(), user.getNickname(), token);
    }
}
