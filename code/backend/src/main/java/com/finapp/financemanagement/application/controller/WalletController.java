package com.finapp.financemanagement.application.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.financemanagement.domain.dto.wallet.GetWalletResponseDTO;
import com.finapp.financemanagement.domain.dto.wallet.SaveWalletRequestDTO;
import com.finapp.financemanagement.domain.dto.wallet.SaveWalletResponseDTO;
import com.finapp.financemanagement.domain.dto.wallet.UpdateWalletRequestDTO;
import com.finapp.financemanagement.domain.dto.wallet.UpdateWalletResponseDTO;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.service.WalletService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Controller class for managing wallets.
 */
@RestController
@RequestMapping("/wallet")
@Validated
public class WalletController {

    @Autowired
    private WalletService walletService;

    /**
     * Retrieves a wallet by its ID.
     *
     * @param id The ID of the wallet to retrieve.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetWalletResponseDTO> getWallet(@PathVariable @NotNull UUID id) {
        GetWalletResponseDTO response = this.walletService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Saves a new wallet.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The data of the wallet to save.
     * @return ResponseEntity containing the response data.
     */
    @PostMapping("")
    public ResponseEntity<SaveWalletResponseDTO> saveWallet(Authentication authenticatedUser, @RequestBody @Valid SaveWalletRequestDTO data) {
        SaveWalletResponseDTO response = this.walletService.save((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Updates an existing wallet.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The updated data of the wallet.
     * @return ResponseEntity containing the response data.
     */
    @PutMapping("")
    public ResponseEntity<UpdateWalletResponseDTO> updateWallet(Authentication authenticatedUser, @RequestBody @Valid UpdateWalletRequestDTO data) {
        UpdateWalletResponseDTO response = this.walletService.update((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Deletes a wallet by its ID.
     *
     * @param authenticatedUser The authenticated user.
     * @param id                The ID of the wallet to delete.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(Authentication authenticatedUser, @PathVariable @NotNull UUID id) {
        this.walletService.delete((User) authenticatedUser.getPrincipal(), id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
