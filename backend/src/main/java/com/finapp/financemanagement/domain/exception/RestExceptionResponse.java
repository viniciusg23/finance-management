package com.finapp.financemanagement.domain.exception;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents the response format for exceptions in the financial management application.
 */
@AllArgsConstructor
@Data
public class RestExceptionResponse {

    private Integer status;
    private Timestamp timestamp;
    private String reason;
    private String error;
    private String details;
    
}
