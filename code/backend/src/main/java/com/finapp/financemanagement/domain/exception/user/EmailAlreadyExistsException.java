package com.finapp.financemanagement.domain.exception.user;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.UserError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a user with the provided email already exists.
 * This exception is typically thrown when attempting to create a new user with an email that is already in use.
 */
public class EmailAlreadyExistsException extends FinanceManagementApplicationException {
    
    /**
     * Constructs an EmailAlreadyExistsException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that a user with the provided email already exists.
     */
    public EmailAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, UserError.USER_EMAIL_ALREADY_EXISTS.getError());
    }
}
