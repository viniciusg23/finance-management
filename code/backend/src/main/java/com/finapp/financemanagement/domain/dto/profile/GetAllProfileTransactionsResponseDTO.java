package com.finapp.financemanagement.domain.dto.profile;

import java.util.List;

import com.finapp.financemanagement.domain.dto.transaction.GetTransactionResponseDTO;
import com.finapp.financemanagement.domain.model.Profile;

/**
 * Data Transfer Object (DTO) representing the response for retrieving all transactions associated with a profile.
 * This DTO contains a list of transactions associated with the profile.
 */
public record GetAllProfileTransactionsResponseDTO(
    List<GetTransactionResponseDTO> transactions
) {
    /**
     * Constructs a GetAllProfileTransactionsResponseDTO object based on the provided Profile entity.
     *
     * @param profile the Profile entity to map to the DTO
     */
    public GetAllProfileTransactionsResponseDTO(Profile profile){
        this(profile.getTransactions().stream().map(GetTransactionResponseDTO::new).toList());
    }
}
