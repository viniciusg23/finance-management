package com.finapp.financemanagement.domain.dto.icon;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing the response for retrieving an icon.
 * This DTO contains information about an icon including its ID, name,
 * third-party ID,
 * and creation timestamp.
 */
public record GetAllIconsResponseDTO(
        List<GetIconResponseDTO> icons
) {

}
