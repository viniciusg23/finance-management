package com.finapp.financemanagement.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.service.SharingService;

import jakarta.validation.constraints.NotNull;

/**
 * Controller class for managing operations related to sharing.
 */
@RestController
@RequestMapping("/sharing")
@Validated
public class SharingController {

    @Autowired
    private SharingService sharingService;

    /**
     * Deletes a sharing entry by its ID.
     *
     * @param authenticatedUser The authenticated user.
     * @param id                The ID of the sharing entry to delete.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSharing(Authentication authenticatedUser, @PathVariable @NotNull UUID id) {
        this.sharingService.delete((User) authenticatedUser.getPrincipal(), id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
