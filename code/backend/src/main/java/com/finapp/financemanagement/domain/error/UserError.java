package com.finapp.financemanagement.domain.error;

/**
 * Enumeration representing possible errors related to users in the financial management application.
 * It includes errors such as USER_NOT_FOUND, USER_NICKNAME_NOT_FOUND, USER_EMAIL_ALREADY_EXISTS,
 * USER_NICKNAME_ALREADY_EXISTS, INVALID_LOGIN_CREDENTIALS, and INVALID_AUTHENTICATION.
 */
public enum UserError {
    
    /**
     * Error indicating that a user with the specified ID could not be found.
     */
    USER_NOT_FOUND(new FinanceManagementApplicationError("User Not Found", "There isen\'t any user with this id")),
    
    /**
     * Error indicating that a user with the specified nickname could not be found.
     */
    USER_NICKNAME_NOT_FOUND(new FinanceManagementApplicationError("Nickname Not Found", "There isen\'t any user with this nickname")),
    
    /**
     * Error indicating that the provided email for user registration is already in use.
     */
    USER_EMAIL_ALREADY_EXISTS(new FinanceManagementApplicationError("Email Already Exists", "This user email is already in use")),
    
    /**
     * Error indicating that the provided nickname for user registration is already in use.
     */
    USER_NICKNAME_ALREADY_EXISTS(new FinanceManagementApplicationError("Nickname Already Exists", "This user nickname is already in use")),
    
    /**
     * Error indicating that the provided login credentials are invalid.
     */
    INVALID_LOGIN_CREDENTIALS(new FinanceManagementApplicationError("Invalid Authentication", "The email or password is incorrect")),
    
    /**
     * Error indicating that there was an error during authentication.
     */
    INVALID_AUTHENTICATION(new FinanceManagementApplicationError("Invalid Authentication", "There was an error during authentication, re-login and try again"));

    private final FinanceManagementApplicationError error;

    /**
     * Constructs a UserError enum instance with the provided FinanceManagementApplicationError.
     *
     * @param error the FinanceManagementApplicationError associated with the enum value
     */
    UserError(FinanceManagementApplicationError error) {
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

