package com.finapp.financemanagement.application.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;
import com.finapp.financemanagement.domain.exception.RestExceptionResponse;

/**
 * Global exception handler for RESTful APIs.
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    /**
     * Handles FinanceManagementApplicationException.
     *
     * @param exception The FinanceManagementApplicationException to handle.
     * @return ResponseEntity containing the exception response.
     */
    @ExceptionHandler(FinanceManagementApplicationException.class)
    private ResponseEntity<RestExceptionResponse> financeManagementApplicationExceptionHandler(FinanceManagementApplicationException exception) {
        return ResponseEntity.status(exception.getResponse().getStatus()).body(exception.getResponse());
    }

    /**
     * Handles runtime exceptions.
     *
     * @param exception The runtime exception to handle.
     * @return ResponseEntity containing the exception response.
     */
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestExceptionResponse> runtimeExceptionHandler(RuntimeException exception) {
        RestExceptionResponse response = new RestExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), FinanceManagementApplicationException.getCurrentTimestamp(), "Internal Server Error", "Internal Server Error", exception.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
