package com.finapp.financemanagement.domain.error;

/**
 * Enumeration representing possible errors related to profiles in the financial management application.
 * It includes errors such as PROFILE_NOT_FOUND and PROFILE_NAME_ALREADY_EXISTS.
 */
public enum ProfileError {
    
    /**
     * Error indicating that a profile with the specified ID could not be found.
     */
    PROFILE_NOT_FOUND(new FinanceManagementApplicationError("Profile Not Found", "There isen\'t any profile with this id")),
    
    /**
     * Error indicating that a profile with the specified name already exists.
     */
    PROFILE_NAME_ALREADY_EXISTS(new FinanceManagementApplicationError("Name Already Exists", "This profile name is already in use"));

    private final FinanceManagementApplicationError error;

    /**
     * Constructs a ProfileError enum instance with the provided FinanceManagementApplicationError.
     *
     * @param error the FinanceManagementApplicationError associated with the enum value
     */
    ProfileError(FinanceManagementApplicationError error) {
        this.error = error;
    }

    /**
     * Retrieves the FinanceManagementApplicationError associated with the enum value.
     *
     * @return the FinanceManagementApplicationError object representing the error
     */
    public FinanceManagementApplicationError getError() {
        return error;
    }
}
