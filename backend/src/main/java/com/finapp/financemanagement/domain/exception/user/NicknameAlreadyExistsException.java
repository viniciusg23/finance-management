package com.finapp.financemanagement.domain.exception.user;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.UserError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that the nickname provided by the user already exists.
 * This exception is typically thrown when a user attempts to use a nickname that is already in use by another user.
 */
public class NicknameAlreadyExistsException extends FinanceManagementApplicationException{
    
    /**
     * Constructs a NicknameAlreadyExistsException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that the provided nickname already exists.
     */
    public NicknameAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, UserError.USER_NICKNAME_ALREADY_EXISTS.getError());
    }
}
