package com.finapp.financemanagement.domain.exception.utils;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.UtilsError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that an invalid UUID is encountered.
 * This exception is typically thrown when an operation expects a valid UUID but receives an invalid one.
 */
public class InvalidUUIDException extends FinanceManagementApplicationException {
    
    /**
     * Constructs an InvalidUUIDException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that the UUID is invalid.
     */
    public InvalidUUIDException() {
        super(HttpStatus.BAD_REQUEST, UtilsError.INVALID_UUID.getError());
    }
}
