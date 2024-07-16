package com.finapp.financemanagement.domain.error;

/**
 * Enumeration representing possible utility-related errors in the financial management application.
 * It includes errors such as INVALID_UUID.
 */
public enum UtilsError {

    /**
     * Error indicating that the provided UUID is invalid.
     */
    INVALID_UUID(new FinanceManagementApplicationError("Invalid UUID", "The provided UUID is invalid"));

    private final FinanceManagementApplicationError error;

    /**
     * Constructs a UtilsError enum instance with the provided FinanceManagementApplicationError.
     *
     * @param error the FinanceManagementApplicationError associated with the enum value
     */
    UtilsError(FinanceManagementApplicationError error) {
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
