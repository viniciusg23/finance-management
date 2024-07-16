package com.finapp.financemanagement.domain.exception.sharing;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.SharingError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a sharing was not found.
 * This exception is typically thrown when attempting to retrieve or manipulate a sharing that does not exist in the system.
 */
public class SharingNotFoundException extends FinanceManagementApplicationException {
    
    /**
     * Constructs a SharingNotFoundException with a default HTTP status of NOT_FOUND
     * and an error message indicating that the sharing was not found.
     */
    public SharingNotFoundException(){
        super(HttpStatus.NOT_FOUND, SharingError.SHARING_NOT_FOUND.getError());
    }
}
