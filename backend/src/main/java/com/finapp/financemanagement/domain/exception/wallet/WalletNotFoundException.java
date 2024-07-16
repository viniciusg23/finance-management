package com.finapp.financemanagement.domain.exception.wallet;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.WalletError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a wallet was not found.
 * This exception is typically thrown when attempting to retrieve or perform operations on a wallet that does not exist.
 */
public class WalletNotFoundException extends FinanceManagementApplicationException {
    
    /**
     * Constructs a WalletNotFoundException with a default HTTP status of NOT_FOUND
     * and an error message indicating that the wallet was not found.
     */
    public WalletNotFoundException() {
        super(HttpStatus.NOT_FOUND, WalletError.WALLET_NOT_FOUND.getError());
    }
}
