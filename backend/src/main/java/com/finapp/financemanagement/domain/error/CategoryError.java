package com.finapp.financemanagement.domain.error;

/**
 * Enumeration representing possible errors related to categories in the financial management application.
 * It includes errors such as CATEGORY_NOT_FOUND and CATEGORY_NAME_ALREADY_EXISTS.
 */
public enum CategoryError {

    /**
     * Error indicating that a category with the specified ID could not be found.
     */
    CATEGORY_NOT_FOUND(new FinanceManagementApplicationError("Category Not Found", "There isen\'t any category with this id")),
    
    /**
     * Error indicating that a category with the specified name already exists.
     */
    CATEGORY_NAME_ALREADY_EXISTS(new FinanceManagementApplicationError("Category Name Already Exists", "There is already a category with this name"));

    private final FinanceManagementApplicationError error;

    /**
     * Constructs a CategoryError enum instance with the provided FinanceManagementApplicationError.
     *
     * @param error the FinanceManagementApplicationError associated with the enum value
     */
    CategoryError(FinanceManagementApplicationError error) {
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
