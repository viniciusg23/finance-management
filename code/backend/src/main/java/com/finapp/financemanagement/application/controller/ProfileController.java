package com.finapp.financemanagement.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.financemanagement.domain.dto.profile.GetAllProfileCategoriesResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.GetAllProfileTransactionsResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.GetAllProfileWalletsResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.GetProfileResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.SaveProfileRequestDTO;
import com.finapp.financemanagement.domain.dto.profile.SaveProfileResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.UpdateProfileRequestDTO;
import com.finapp.financemanagement.domain.dto.profile.UpdateProfileResponseDTO;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.service.ProfileService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Controller class for managing operations related to profiles.
 */
@RestController
@RequestMapping("/profile")
@Validated
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    /**
     * Retrieves a profile by its ID.
     *
     * @param id The ID of the profile to retrieve.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetProfileResponseDTO> getProfile(@PathVariable @NotNull UUID id) {
        GetProfileResponseDTO response = this.profileService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves all categories associated with the user's profile.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The request data.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<GetAllProfileCategoriesResponseDTO> getAllProfileCategories(Authentication authenticatedUser, @PathVariable @NotNull UUID id) {
        GetAllProfileCategoriesResponseDTO response = this.profileService.getAllCategories((User) authenticatedUser.getPrincipal(), id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves all wallets associated with the user's profile.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The request data.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/wallets/{id}")
    public ResponseEntity<GetAllProfileWalletsResponseDTO> getAllProfileWallets(Authentication authenticatedUser, @PathVariable @NotNull UUID id) {
        GetAllProfileWalletsResponseDTO response = this.profileService.getAllWallets((User) authenticatedUser.getPrincipal(), id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves all transactions associated with the user's profile.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The request data.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/transactions/{id}")
    public ResponseEntity<GetAllProfileTransactionsResponseDTO> getAllProfileTransactions(Authentication authenticatedUser, @PathVariable @NotNull UUID id) {
        GetAllProfileTransactionsResponseDTO response = this.profileService.getAllTransactions((User) authenticatedUser.getPrincipal(), id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Saves a new profile.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The data of the profile to save.
     * @return ResponseEntity containing the response data.
     */
    @PostMapping("")
    public ResponseEntity<SaveProfileResponseDTO> saveProfile(Authentication authenticatedUser, @RequestBody @Valid SaveProfileRequestDTO data) {
        SaveProfileResponseDTO response = this.profileService.save((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Updates an existing profile.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The updated data of the profile.
     * @return ResponseEntity containing the response data.
     */
    @PutMapping("")
    public ResponseEntity<UpdateProfileResponseDTO> updateProfile(Authentication authenticatedUser, @RequestBody @Valid UpdateProfileRequestDTO data) {
        UpdateProfileResponseDTO response = this.profileService.update((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Deletes a profile by its ID.
     *
     * @param authenticatedUser The authenticated user.
     * @param id                The ID of the profile to delete.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(Authentication authenticatedUser, @PathVariable @NotNull UUID id) {
        this.profileService.delete((User) authenticatedUser.getPrincipal(), id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
