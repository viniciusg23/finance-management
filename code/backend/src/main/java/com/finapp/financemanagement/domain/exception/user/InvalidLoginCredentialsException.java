package com.finapp.financemanagement.domain.exception.user;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.UserError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that login credentials provided by the user are invalid.
 * This exception is typically thrown when the email or password provided during login is incorrect.
 */
public class InvalidLoginCredentialsException extends FinanceManagementApplicationException{
    
    /**
     * Constructs an InvalidLoginCredentialsException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that the login credentials provided are invalid.
     */
    public InvalidLoginCredentialsException() {
        super(HttpStatus.BAD_REQUEST, UserError.INVALID_LOGIN_CREDENTIALS.getError());
    }
}
