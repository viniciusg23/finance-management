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

import com.finapp.financemanagement.domain.dto.user.GetAllUserProfilesResponseDTO;
import com.finapp.financemanagement.domain.dto.user.GetAllUsersResponseDTO;
import com.finapp.financemanagement.domain.dto.user.GetUserResponseDTO;
import com.finapp.financemanagement.domain.dto.user.LoginUserRequestDTO;
import com.finapp.financemanagement.domain.dto.user.LoginUserResponseDTO;
import com.finapp.financemanagement.domain.dto.user.SaveUserRequestDTO;
import com.finapp.financemanagement.domain.dto.user.SaveUserResponseDTO;
import com.finapp.financemanagement.domain.dto.user.UpdateUserRequestDTO;
import com.finapp.financemanagement.domain.dto.user.UpdateUserResponseDTO;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Controller class for managing user-related operations.
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a user by its ID.
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponseDTO> getUser(@PathVariable @NotNull UUID id) {
        GetUserResponseDTO response = this.userService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves all users.
     *
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("")
    public ResponseEntity<GetAllUsersResponseDTO> getAllUser() {
        GetAllUsersResponseDTO response = this.userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves all profiles associated with the authenticated user.
     *
     * @param authenticatedUser The authenticated user.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/profiles")
    public ResponseEntity<GetAllUserProfilesResponseDTO> getAllProfiles(Authentication authenticatedUser) {
        GetAllUserProfilesResponseDTO response = this.userService.getAllProfiles((User) authenticatedUser.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Registers a new user.
     *
     * @param data The data of the user to save.
     * @return ResponseEntity containing the response data.
     */
    @PostMapping("")
    public ResponseEntity<SaveUserResponseDTO> saveUser(@RequestBody @Valid SaveUserRequestDTO data) {
        SaveUserResponseDTO response = this.userService.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Authenticates a user.
     *
     * @param data The login credentials of the user.
     * @return ResponseEntity containing the response data.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> login(@RequestBody @Valid LoginUserRequestDTO data) {
        LoginUserResponseDTO response = this.userService.authenticate(data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Updates an existing user.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The updated data of the user.
     * @return ResponseEntity containing the response data.
     */
    @PutMapping("")
    public ResponseEntity<UpdateUserResponseDTO> updateUser(Authentication authenticatedUser, @RequestBody @Valid UpdateUserRequestDTO data) {
        UpdateUserResponseDTO response = this.userService.update((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Deletes the authenticated user.
     *
     * @param authenticatedUser The authenticated user.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("")
    public ResponseEntity<Void> deleteUser(Authentication authenticatedUser) {
        this.userService.delete((User) authenticatedUser.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
