package com.finapp.financemanagement.domain.error;

/**
 * Enumeration representing possible errors related to wallets in the financial management application.
 * It includes errors such as WALLET_NOT_FOUND and NAME_ALREADY_EXISTS.
 */
public enum WalletError {
    
    /**
     * Error indicating that the wallet with the specified ID was not found.
     */
    WALLET_NOT_FOUND(new FinanceManagementApplicationError("Wallet Not Found", "There isen\'t any wallet with this id")),
    
    /**
     * Error indicating that the specified wallet name already exists.
     */
    NAME_ALREADY_EXISTS(new FinanceManagementApplicationError("Name Already Exists", "This wallet name is already in use"));

    private final FinanceManagementApplicationError error;

    /**
     * Constructs a WalletError enum instance with the provided FinanceManagementApplicationError.
     *
     * @param error the FinanceManagementApplicationError associated with the enum value
     */
    WalletError(FinanceManagementApplicationError error) {
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
