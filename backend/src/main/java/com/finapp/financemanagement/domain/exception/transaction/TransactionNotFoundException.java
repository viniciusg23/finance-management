package com.finapp.financemanagement.domain.exception.transaction;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.TransactionError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a transaction is not found.
 * This exception is typically thrown when attempting to retrieve or operate on a transaction that does not exist.
 */
public class TransactionNotFoundException extends FinanceManagementApplicationException {
    
    /**
     * Constructs a TransactionNotFoundException with a default HTTP status of NOT_FOUND
     * and an error message indicating that the transaction is not found.
     */
    public TransactionNotFoundException(){
        super(HttpStatus.NOT_FOUND, TransactionError.TRANSACTION_NOT_FOUND.getError());
    }
}
