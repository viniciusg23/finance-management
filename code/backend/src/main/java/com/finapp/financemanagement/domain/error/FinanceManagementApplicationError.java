package com.finapp.financemanagement.domain.error;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents an error in the finance management application.
 * Contains fields for the error name and description.
 */
@Data
@AllArgsConstructor
public class FinanceManagementApplicationError {
    private String name;
    private String description;
}
