package com.finapp.financemanagement.domain.exception.sharingInvite;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.SharingInviteError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;


/**
 * Exception indicating that an invite has already been accepted.
 * This exception is typically thrown when attempting to accept an invite that has already been accepted.
 */
public class InviteAlreadyAcceptedException extends FinanceManagementApplicationException {
    
    /**
     * Constructs an InviteAlreadyAcceptedException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that the invite has already been accepted.
     */
    public InviteAlreadyAcceptedException() {
        super(HttpStatus.BAD_REQUEST, SharingInviteError.INVITE_ALREADY_ACCEPTED.getError());
    }
}
