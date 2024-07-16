package com.finapp.financemanagement.domain.exception.icon;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.IconError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a third-party ID associated with an icon already exists.
 * This exception is typically thrown when attempting to create or update an icon with a third-party ID that is already in use.
 */
public class ThirdPartyIdAlreadyExistsException extends FinanceManagementApplicationException {
    
    /**
     * Constructs a ThirdPartyIdAlreadyExistsException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that the third-party ID already exists.
     */
    public ThirdPartyIdAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, IconError.THIRD_PARTY_ID_ALREADY_EXISTS.getError());
    }
}
