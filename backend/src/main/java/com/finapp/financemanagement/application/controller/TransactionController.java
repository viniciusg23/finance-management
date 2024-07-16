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

import com.finapp.financemanagement.domain.dto.transaction.GetTransactionResponseDTO;
import com.finapp.financemanagement.domain.dto.transaction.SaveTransactionRequestDTO;
import com.finapp.financemanagement.domain.dto.transaction.SaveTransactionResponseDTO;
import com.finapp.financemanagement.domain.dto.transaction.UpdateTransactionRequestDTO;
import com.finapp.financemanagement.domain.dto.transaction.UpdateTransactionResponseDTO;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.service.TransactionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Controller class for managing operations related to transactions.
 */
@RestController
@RequestMapping("/transaction")
@Validated
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    /**
     * Retrieves a transaction by its ID.
     * @param authenticatedUser The authenticated user.
     * @param id                The ID of the transaction to retrieve.
     * @return  ResponseEntity containing the response data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetTransactionResponseDTO> getTransaction(Authentication authenticatedUser, @PathVariable @NotNull UUID id){
        GetTransactionResponseDTO response = this.transactionService.get((User) authenticatedUser.getPrincipal(), id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Records a credit transaction.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The data of the credit transaction.
     * @return ResponseEntity containing the response data.
     */
    @PostMapping("/credit")
    public ResponseEntity<SaveTransactionResponseDTO> creditTransaction(Authentication authenticatedUser, @RequestBody @Valid SaveTransactionRequestDTO data) {
        SaveTransactionResponseDTO response = this.transactionService.credit((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Records a debit transaction.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The data of the debit transaction.
     * @return ResponseEntity containing the response data.
     */
    @PostMapping("/debit")
    public ResponseEntity<SaveTransactionResponseDTO> debitTransaction(Authentication authenticatedUser, @RequestBody @Valid SaveTransactionRequestDTO data) {
        SaveTransactionResponseDTO response = this.transactionService.debit((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Updates an existing transaction.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The updated data of the transaction.
     * @return ResponseEntity containing the response data.
     */
    @PutMapping("")
    public ResponseEntity<UpdateTransactionResponseDTO> updateTransaction(Authentication authenticatedUser, @RequestBody @Valid UpdateTransactionRequestDTO data) {
        UpdateTransactionResponseDTO response = this.transactionService.update((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param authenticatedUser The authenticated user.
     * @param id                The ID of the transaction to delete.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<UpdateTransactionResponseDTO> deleteTransaction(Authentication authenticatedUser, @PathVariable @NotNull UUID id) {
        this.transactionService.delete((User) authenticatedUser.getPrincipal(), id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
