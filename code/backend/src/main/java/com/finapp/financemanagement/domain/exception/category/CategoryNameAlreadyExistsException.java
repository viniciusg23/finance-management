package com.finapp.financemanagement.domain.exception.category;

import org.springframework.http.HttpStatus;

import com.finapp.financemanagement.domain.error.CategoryError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;

/**
 * Exception indicating that a category with the same name already exists.
 * This exception is typically thrown when attempting to create a new category with a name that is already in use.
 */
public class CategoryNameAlreadyExistsException extends FinanceManagementApplicationException {
    
    /**
     * Constructs a CategoryNameAlreadyExistsException with a default HTTP status of BAD_REQUEST
     * and an error message indicating that the category name already exists.
     */
    public CategoryNameAlreadyExistsException(){
        super(HttpStatus.BAD_REQUEST, CategoryError.CATEGORY_NAME_ALREADY_EXISTS.getError());
    }
}
