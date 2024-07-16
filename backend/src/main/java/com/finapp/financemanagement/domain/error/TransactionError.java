package com.finapp.financemanagement.domain.error;

/**
 * Enumeration representing possible errors related to transactions in the financial management application.
 * It includes errors such as TRANSACTION_NOT_FOUND.
 */
public enum TransactionError {

    /**
     * Error indicating that a transaction with the specified ID could not be found.
     */
    TRANSACTION_NOT_FOUND(new FinanceManagementApplicationError("Transaction Not Found", "There isen\'t any transaction with this id")),;
    
    private final FinanceManagementApplicationError error;

    /**
     * Constructs a TransactionError enum instance with the provided FinanceManagementApplicationError.
     *
     * @param error the FinanceManagementApplicationError associated with the enum value
     */
    TransactionError(FinanceManagementApplicationError error) {
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
