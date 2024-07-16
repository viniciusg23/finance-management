package com.finapp.financemanagement.domain.error;

/**
 * Enumeration representing possible errors related to sharing in the financial management application.
 * It includes errors such as SHARING_NOT_FOUND.
 */
public enum SharingError {

    /**
     * Error indicating that a sharing with the specified ID could not be found.
     */
    SHARING_NOT_FOUND(new FinanceManagementApplicationError("Sharing Not Found", "There isen\'t any sharing with this id")),;
    
    private final FinanceManagementApplicationError error;

    /**
     * Constructs a SharingError enum instance with the provided FinanceManagementApplicationError.
     *
     * @param error the FinanceManagementApplicationError associated with the enum value
     */
    SharingError(FinanceManagementApplicationError error) {
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
