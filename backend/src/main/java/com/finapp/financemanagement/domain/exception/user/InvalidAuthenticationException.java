package com.finapp.financemanagement.domain.exception.user;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.UserError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that authentication failed due to invalid credentials or other authentication-related issues.
 * This exception is typically thrown when there is an error during authentication, such as incorrect email or password.
 */
public class InvalidAuthenticationException extends FinanceManagementApplicationException {
    
    /**
     * Constructs an InvalidAuthenticationException with a default HTTP status of UNAUTHORIZED
     * and an error message indicating that authentication failed due to an invalid authentication attempt.
     */
    public InvalidAuthenticationException() {
        super(HttpStatus.UNAUTHORIZED, UserError.INVALID_AUTHENTICATION.getError());
    }
}
