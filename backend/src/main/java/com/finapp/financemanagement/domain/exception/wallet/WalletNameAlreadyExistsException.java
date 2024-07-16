package com.finapp.financemanagement.domain.exception.wallet;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.WalletError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a wallet name already exists.
 * This exception is typically thrown when attempting to create or update a wallet with a name that is already in use.
 */
public class WalletNameAlreadyExistsException extends FinanceManagementApplicationException{
    
    /**
     * Constructs a WalletNameAlreadyExistsException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that the wallet name already exists.
     */
    public WalletNameAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, WalletError.NAME_ALREADY_EXISTS.getError());
    }
}
