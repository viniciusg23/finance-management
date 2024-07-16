package com.finapp.financemanagement.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.financemanagement.domain.dto.sharingInvite.GetAllUserInvitesResponseDTO;
import com.finapp.financemanagement.domain.dto.sharingInvite.SaveSharingInviteRequestDTO;
import com.finapp.financemanagement.domain.dto.sharingInvite.SaveSharingInviteResponseDTO;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.service.SharingInviteService;

import jakarta.validation.Valid;

/**
 * Controller class for managing sharing invitations.
 */
@RestController
@RequestMapping("/sharing/invite")
public class SharingInviteController {

    @Autowired
    private SharingInviteService sharingInviteService;


    /**
     * Retrieves all sharing invitations received by the authenticated user.
     *
     * @param authenticatedUser The authenticated user.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/received")
    public ResponseEntity<GetAllUserInvitesResponseDTO> getAllUserReceivedInvites(Authentication authenticatedUser) {
        GetAllUserInvitesResponseDTO response = this.sharingInviteService.findAllReceivedByUserId((User) authenticatedUser.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves all sharing invitations sent by the authenticated user.
     *
     * @param authenticatedUser The authenticated user.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/sent")
    public ResponseEntity<GetAllUserInvitesResponseDTO> getAllUserSentInvites(Authentication authenticatedUser) {
        GetAllUserInvitesResponseDTO response = this.sharingInviteService.findAllSentByUserId((User) authenticatedUser.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Sends a sharing invitation to another user.
     *
     * @param authenticatedUser The authenticated user.
     * @param data               The data of the sharing invitation.
     * @return ResponseEntity containing the response data.
     */
    @PostMapping("")
    public ResponseEntity<SaveSharingInviteResponseDTO> inviteUser(Authentication authenticatedUser, @RequestBody @Valid SaveSharingInviteRequestDTO data) {
        SaveSharingInviteResponseDTO response = this.sharingInviteService.save((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Accepts a sharing invitation.
     * @param authenticatedUser The authenticated user.
     * @param sharingInviteId   The ID of the sharing invitation to accept.
     * @return  ResponseEntity indicating the success of the operation.
     */
    @GetMapping("/accept/{sharingInviteId}")
    public ResponseEntity<Void> acceptInvite(Authentication authenticatedUser, @PathVariable @Valid UUID sharingInviteId) {
        this.sharingInviteService.accept((User) authenticatedUser.getPrincipal(), sharingInviteId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Accepts a sharing invitation.
     * @param authenticatedUser The authenticated user.
     * @param sharingInviteId   The ID of the sharing invitation to refuse.
     * @return  ResponseEntity indicating the success of the operation.
     */
    @GetMapping("/refuse/{sharingInviteId}")
    public ResponseEntity<Void> refuseInvite(Authentication authenticatedUser, @PathVariable @Valid UUID sharingInviteId) {
        this.sharingInviteService.refuse((User) authenticatedUser.getPrincipal(), sharingInviteId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
