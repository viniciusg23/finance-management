package com.finapp.financemanagement.domain.exception.user;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.UserError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that the provided nickname does not exist.
 * This exception is typically thrown when a user attempts to access a user profile by nickname,
 * but the nickname does not exist.
 */
public class NicknameNotFoundException extends FinanceManagementApplicationException {
    
    /**
     * Constructs a NicknameNotFoundException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that the provided nickname does not exist.
     */
    public NicknameNotFoundException() {
        super(HttpStatus.BAD_REQUEST, UserError.USER_NICKNAME_NOT_FOUND.getError());
    }
}
