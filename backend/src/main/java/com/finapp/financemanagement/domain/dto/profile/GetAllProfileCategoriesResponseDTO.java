package com.finapp.financemanagement.domain.dto.profile;

import java.util.List;
import com.finapp.financemanagement.domain.dto.category.GetCategoryResponseDTO;
import com.finapp.financemanagement.domain.model.Profile;

/**
 * Data Transfer Object (DTO) representing the response for retrieving all categories associated with a profile.
 * This DTO contains a list of categories associated with the profile.
 */
public record GetAllProfileCategoriesResponseDTO(
        List<GetCategoryResponseDTO> categories
) {
    /**
     * Constructs a GetAllProfileCategoriesResponseDTO object based on the provided Profile entity.
     *
     * @param profile the Profile entity to map to the DTO
     */
    public GetAllProfileCategoriesResponseDTO(Profile profile){
        this(profile.getCategories().stream().map(GetCategoryResponseDTO::new).toList());
    }
}
