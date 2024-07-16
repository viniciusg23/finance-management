package com.finapp.financemanagement.domain.exception.icon;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.IconError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that an icon could not be found.
 * This exception is typically thrown when attempting to retrieve or operate on an icon that does not exist.
 */
public class IconNotFoundException extends FinanceManagementApplicationException {
    
    /**
     * Constructs an IconNotFoundException with a default HTTP status of NOT_FOUND
     * and an error message indicating that the icon was not found.
     */
    public IconNotFoundException() {
        super(HttpStatus.NOT_FOUND, IconError.ICON_NOT_FOUND.getError());
    }
}
