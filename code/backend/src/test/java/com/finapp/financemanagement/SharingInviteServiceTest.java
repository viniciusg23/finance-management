package com.finapp.financemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.finapp.financemanagement.domain.dto.sharingInvite.GetAllUserInvitesResponseDTO;
import com.finapp.financemanagement.domain.dto.sharingInvite.GetUserInviteResponseDTO;
import com.finapp.financemanagement.domain.dto.sharingInvite.SaveSharingInviteRequestDTO;
import com.finapp.financemanagement.domain.dto.sharingInvite.SaveSharingInviteResponseDTO;
import com.finapp.financemanagement.domain.enums.SharingInviteStatus;
import com.finapp.financemanagement.domain.exception.sharingInvite.InviteAlreadyAcceptedException;
import com.finapp.financemanagement.domain.exception.sharingInvite.InviteAlreadyRefusedException;
import com.finapp.financemanagement.domain.exception.sharingInvite.InviteNotFoundException;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.SharingInvite;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.ISharingInviteRepository;
import com.finapp.financemanagement.domain.service.ProfileService;
import com.finapp.financemanagement.domain.service.SharingInviteService;
import com.finapp.financemanagement.domain.service.SharingService;
import com.finapp.financemanagement.domain.service.UserService;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;

public class SharingInviteServiceTest {

    @InjectMocks
    private SharingInviteService sharingInviteService;

    @Mock
    private ISharingInviteRepository sharingInviteRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProfileService profileService;

    @Mock
    private SharingService sharingService;

    private User sender;
    private User receiver;
    private Profile profile;
    private SharingInvite sharingInvite;
    private UUID sharingInviteId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        sender = User.builder()
                .id(UUID.randomUUID())
                .name("Usuário Remetente")
                .nickname("remetente")
                .email("remetente@email.com")
                .createdAt(LocalDateTime.now())
                .build();
        receiver = User.builder()
                .id(UUID.randomUUID())
                .name("Usuário Destinatário")
                .nickname("destinatario")
                .email("destinatario@email.com")
                .createdAt(LocalDateTime.now())
                .build();
        profile = Profile.builder()
                .id(UUID.randomUUID())
                .name("Perfil para Compartilhar")
                .user(sender)
                .createdAt(LocalDateTime.now())
                .build();
        sharingInvite = SharingInvite.builder()
                .id(UUID.randomUUID())
                .sender(sender)
                .receiver(receiver)
                .profile(profile)
                .status(SharingInviteStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        sharingInviteId = sharingInvite.getId();
    }

    @Test
    public void testFindById_ShouldReturnSharingInviteWhenFound() {
        when(sharingInviteRepository.findById(sharingInviteId)).thenReturn(Optional.of(sharingInvite));

        SharingInvite result = sharingInviteService.findById(sharingInviteId);

        assertEquals(sharingInvite, result);
    }

    @Test
    public void testFindById_ShouldThrowInviteNotFoundExceptionWhenNotFound() {
        when(sharingInviteRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(InviteNotFoundException.class, () -> sharingInviteService.findById(sharingInviteId));
    }

    @Test
    public void testFindAllReceivedByUserId_ShouldReturnListOfReceivedInvites() {
        List<SharingInvite> receivedInvites = List.of(sharingInvite);
        when(sharingInviteRepository.findAllByReceiverId(receiver.getId())).thenReturn(receivedInvites);

        GetAllUserInvitesResponseDTO result = sharingInviteService.findAllReceivedByUserId(receiver);

        assertEquals(new GetAllUserInvitesResponseDTO(
                receivedInvites.stream().map(GetUserInviteResponseDTO::new).toList()), result);
    }

    @Test
    public void testFindAllSentByUserId_ShouldReturnListOfSentInvites() {
        List<SharingInvite> sentInvites = List.of(sharingInvite);
        when(sharingInviteRepository.findAllBySenderId(sender.getId())).thenReturn(sentInvites);

        GetAllUserInvitesResponseDTO result = sharingInviteService.findAllSentByUserId(sender);

        assertEquals(new GetAllUserInvitesResponseDTO(
                sentInvites.stream().map(GetUserInviteResponseDTO::new).toList()), result);
    }

    @Test
    public void testSave_ShouldSaveSharingInviteAndReturnSaveSharingInviteResponseDTO() {
        SaveSharingInviteRequestDTO saveSharingInviteRequestDTO = new SaveSharingInviteRequestDTO(
                receiver.getNickname(), profile.getId().toString());

        when(userService.findById(sender.getId())).thenReturn(sender);
        when(userService.findByNickname(saveSharingInviteRequestDTO.receiverNickname())).thenReturn(receiver);
        when(profileService.findByIdAndUserId(UUIDUtils.stringToUUID(saveSharingInviteRequestDTO.profileId()), sender.getId()))
                .thenReturn(profile);
        when(sharingInviteRepository.save(any(SharingInvite.class))).thenReturn(sharingInvite);

        SaveSharingInviteResponseDTO result = sharingInviteService.save(sender, saveSharingInviteRequestDTO);

        assertEquals(new SaveSharingInviteResponseDTO(sharingInvite), result);
    }

    @Test
    public void testAccept_ShouldChangeInviteStatusToAccepted() {
        when(sharingInviteRepository.findById(sharingInviteId)).thenReturn(Optional.of(sharingInvite));

        sharingInviteService.accept(receiver, sharingInviteId);

        assertEquals(SharingInviteStatus.ACCEPTED, sharingInvite.getStatus());
    }

    @Test
    public void testAccept_ShouldThrowInviteNotFoundExceptionWhenInviteNotFound() {
        when(sharingInviteRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(InviteNotFoundException.class, () -> sharingInviteService.accept(receiver, sharingInviteId));
    }

    @Test
    public void testAccept_ShouldThrowInviteAlreadyAcceptedExceptionWhenInviteAlreadyAccepted() {
        sharingInvite.setStatus(SharingInviteStatus.ACCEPTED);
        when(sharingInviteRepository.findById(sharingInviteId)).thenReturn(Optional.of(sharingInvite));

        assertThrows(InviteAlreadyAcceptedException.class, () -> sharingInviteService.accept(receiver, sharingInviteId));
    }

    @Test
    public void testAccept_ShouldThrowInviteAlreadyRefusedExceptionWhenInviteAlreadyRefused() {
        sharingInvite.setStatus(SharingInviteStatus.REFUSED);
        when(sharingInviteRepository.findById(sharingInviteId)).thenReturn(Optional.of(sharingInvite));

        assertThrows(InviteAlreadyRefusedException.class, () -> sharingInviteService.accept(receiver, sharingInviteId));
    }

    @Test
    public void testRefuse_ShouldChangeInviteStatusToRefused() {
        when(sharingInviteRepository.findById(sharingInviteId)).thenReturn(Optional.of(sharingInvite));

        sharingInviteService.refuse(receiver, sharingInviteId);

        assertEquals(SharingInviteStatus.REFUSED, sharingInvite.getStatus());
    }

    @Test
    public void testRefuse_ShouldThrowInviteNotFoundExceptionWhenInviteNotFound() {
        when(sharingInviteRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(InviteNotFoundException.class, () -> sharingInviteService.refuse(receiver, sharingInviteId));
    }

    @Test
    public void testRefuse_ShouldThrowInviteAlreadyAcceptedExceptionWhenInviteAlreadyAccepted() {
        sharingInvite.setStatus(SharingInviteStatus.ACCEPTED);
        when(sharingInviteRepository.findById(sharingInviteId)).thenReturn(Optional.of(sharingInvite));

        assertThrows(InviteAlreadyAcceptedException.class, () -> sharingInviteService.refuse(receiver, sharingInviteId));
    }

    @Test
    public void testRefuse_ShouldThrowInviteAlreadyRefusedExceptionWhenInviteAlreadyRefused() {
        sharingInvite.setStatus(SharingInviteStatus.REFUSED);
        when(sharingInviteRepository.findById(sharingInviteId)).thenReturn(Optional.of(sharingInvite));

        assertThrows(InviteAlreadyRefusedException.class, () -> sharingInviteService.refuse(receiver, sharingInviteId));
    }
}