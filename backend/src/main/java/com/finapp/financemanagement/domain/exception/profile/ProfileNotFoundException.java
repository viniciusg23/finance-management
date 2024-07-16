package com.finapp.financemanagement.domain.exception.profile;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.ProfileError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a profile was not found.
 * This exception is typically thrown when attempting to retrieve or manipulate a profile that does not exist in the system.
 */
public class ProfileNotFoundException extends FinanceManagementApplicationException {
    
    /**
     * Constructs a ProfileNotFoundException with a default HTTP status of NOT_FOUND
     * and an error message indicating that the profile was not found.
     */
    public ProfileNotFoundException() {
        super(HttpStatus.NOT_FOUND, ProfileError.PROFILE_NOT_FOUND.getError());
    }
}
