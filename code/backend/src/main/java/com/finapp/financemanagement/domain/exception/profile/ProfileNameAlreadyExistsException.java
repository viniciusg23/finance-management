package com.finapp.financemanagement.domain.exception.profile;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.ProfileError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a profile name already exists.
 * This exception is typically thrown when attempting to create or update a profile with a name that is already in use.
 */
public class ProfileNameAlreadyExistsException extends FinanceManagementApplicationException{
    
    /**
     * Constructs a ProfileNameAlreadyExistsException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that the profile name already exists.
     */
    public ProfileNameAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, ProfileError.PROFILE_NAME_ALREADY_EXISTS.getError());
    }
}
