package com.finapp.financemanagement.domain.dto.profile;

import java.util.List;

import com.finapp.financemanagement.domain.dto.wallet.GetWalletResponseDTO;
import com.finapp.financemanagement.domain.model.Profile;

/**
 * Data Transfer Object (DTO) representing the response for retrieving all wallets associated with a profile.
 * This DTO contains a list of wallets associated with the profile.
 */
public record GetAllProfileWalletsResponseDTO(
    List<GetWalletResponseDTO> wallets
) {
    /**
     * Constructs a GetAllProfileWalletsResponseDTO object based on the provided Profile entity.
     *
     * @param profile the Profile entity to map to the DTO
     */
    public GetAllProfileWalletsResponseDTO(Profile profile){
        this(profile.getWallets().stream().map(GetWalletResponseDTO::new).toList());
    }
}
