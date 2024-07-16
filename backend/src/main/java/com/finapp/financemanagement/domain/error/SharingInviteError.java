package com.finapp.financemanagement.domain.error;

/**
 * Enumeration representing possible errors related to sharing invites in the financial management application.
 * It includes errors such as INVITE_NOT_FOUND, INVITE_ALREADY_ACCEPTED, and INVITE_ALREADY_REFUSED.
 */
public enum SharingInviteError {
 
    /**
     * Error indicating that an invite with the specified ID could not be found.
     */
    INVITE_NOT_FOUND(new FinanceManagementApplicationError("Invite Not Found", "There isen\'t any invite with this id")),
    
    /**
     * Error indicating that the invite has already been accepted.
     */
    INVITE_ALREADY_ACCEPTED(new FinanceManagementApplicationError("Invite Already Accepted", "This invite has already been accepted")),
    
    /**
     * Error indicating that the invite has already been refused.
     */
    INVITE_ALREADY_REFUSED(new FinanceManagementApplicationError("Invite Already Refused", "This invite has already been refused")),;  

    private final FinanceManagementApplicationError error;

    /**
     * Constructs a SharingInviteError enum instance with the provided FinanceManagementApplicationError.
     *
     * @param error the FinanceManagementApplicationError associated with the enum value
     */
    SharingInviteError(FinanceManagementApplicationError error) {
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
