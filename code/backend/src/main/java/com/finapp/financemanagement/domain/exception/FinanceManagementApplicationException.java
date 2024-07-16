package com.finapp.financemanagement.domain.exception;

import org.springframework.http.HttpStatus;
import com.finapp.financemanagement.domain.error.FinanceManagementApplicationError;
import lombok.Getter;
import java.sql.Timestamp;

/**
 * Base class for exceptions in the financial management application.
 * This class extends RuntimeException and provides a mechanism for generating exceptions with a standardized response.
 */
@Getter
public abstract class FinanceManagementApplicationException extends RuntimeException {

    private RestExceptionResponse response;

    /**
     * Constructs a FinanceManagementApplicationException with the specified HTTP status, error name, and error description.
     *
     * @param status  the HTTP status code associated with the exception
     * @param error   the FinanceManagementApplicationError containing the error name and description
     */
    public FinanceManagementApplicationException(HttpStatus status, FinanceManagementApplicationError error) {
        super(error.getDescription());
        this.response = new RestExceptionResponse(status.value(), FinanceManagementApplicationException.getCurrentTimestamp(), status.getReasonPhrase(), error.getName(), error.getDescription());
        // log.error("An exception occurred: " + error.getName() + " - " + error.getDescription());
    }

    /**
     * Retrieves the current timestamp.
     *
     * @return the current timestamp as a Timestamp object
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

}
