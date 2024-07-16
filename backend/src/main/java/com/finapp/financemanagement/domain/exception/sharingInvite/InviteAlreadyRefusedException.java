package com.finapp.financemanagement.domain.exception.sharingInvite;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.SharingInviteError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that an invite has already been refused.
 * This exception is typically thrown when attempting to refuse an invite that has already been refused.
 */
public class InviteAlreadyRefusedException extends FinanceManagementApplicationException {
    
    /**
     * Constructs an InviteAlreadyRefusedException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that the invite has already been refused.
     */
    public InviteAlreadyRefusedException() {
        super(HttpStatus.BAD_REQUEST, SharingInviteError.INVITE_ALREADY_REFUSED.getError());
    }
}
