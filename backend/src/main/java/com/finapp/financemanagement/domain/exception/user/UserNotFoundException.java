package com.finapp.financemanagement.domain.exception.user;

import org.springframework.http.HttpStatus;
import com.finapp.financemanagement.domain.error.UserError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a user is not found.
 * This exception is typically thrown when a user cannot be found in the system.
 */
public class UserNotFoundException extends FinanceManagementApplicationException {

    /**
     * Constructs a UserNotFoundException with a default HTTP status of NOT_FOUND
     * and an error message indicating that the user is not found.
     */
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, UserError.USER_NOT_FOUND.getError());
    }

}
