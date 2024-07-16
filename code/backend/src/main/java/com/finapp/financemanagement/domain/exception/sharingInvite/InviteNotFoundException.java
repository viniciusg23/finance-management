package com.finapp.financemanagement.domain.exception.sharingInvite;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.SharingInviteError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that an invite is not found.
 * This exception is typically thrown when attempting to retrieve or operate on an invite that does not exist.
 */
public class InviteNotFoundException extends FinanceManagementApplicationException {
    
    /**
     * Constructs an InviteNotFoundException with a default HTTP status of NOT_FOUND
     * and an error message indicating that the invite is not found.
     */
    public InviteNotFoundException() {
        super(HttpStatus.NOT_FOUND, SharingInviteError.INVITE_NOT_FOUND.getError());
    }
}
