package com.finapp.financemanagement.domain.exception.category;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.CategoryError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a category could not be found.
 * This exception is typically thrown when attempting to retrieve or operate on a category that does not exist.
 */
public class CategoryNotFoundException extends FinanceManagementApplicationException {
    
    /**
     * Constructs a CategoryNotFoundException with a default HTTP status of NOT_FOUND
     * and an error message indicating that the category was not found.
     */
    public CategoryNotFoundException(){
        super(HttpStatus.NOT_FOUND, CategoryError.CATEGORY_NOT_FOUND.getError());
    }
}
