package com.finapp.financemanagement.domain.error;

/**
 * Enumeration representing possible errors related to icons in the financial management application.
 * It includes errors such as ICON_NOT_FOUND and THIRD_PARTY_ID_ALREADY_EXISTS.
 */
public enum IconError {

    /**
     * Error indicating that an icon with the specified ID could not be found.
     */
    ICON_NOT_FOUND(new FinanceManagementApplicationError("Icon Not Found", "There isen\'t any icon with this id")),
    
    /**
     * Error indicating that a third party ID for an icon already exists.
     */
    THIRD_PARTY_ID_ALREADY_EXISTS(new FinanceManagementApplicationError("Third Party Id Already Exists", "This icon third party id is already in use"));

    private final FinanceManagementApplicationError error;

    /**
     * Constructs an IconError enum instance with the provided FinanceManagementApplicationError.
     *
     * @param error the FinanceManagementApplicationError associated with the enum value
     */
    IconError(FinanceManagementApplicationError error) {
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
