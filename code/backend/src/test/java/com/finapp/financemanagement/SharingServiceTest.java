package com.finapp.financemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.finapp.financemanagement.domain.exception.profile.ProfileNotFoundException;
import com.finapp.financemanagement.domain.exception.sharing.SharingNotFoundException;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.Sharing;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.ISharingRepository;
import com.finapp.financemanagement.domain.service.SharingService;

public class SharingServiceTest {

    @InjectMocks
    private SharingService sharingService;

    @Mock
    private ISharingRepository sharingRepository;

    private User owner;
    private User guest;
    private Profile profile;
    private Sharing sharing;
    private UUID sharingId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        owner = User.builder()
                .id(UUID.randomUUID())
                .name("Usuário Proprietário")
                .nickname("proprietario")
                .email("proprietario@email.com")
                .build();
        guest = User.builder()
                .id(UUID.randomUUID())
                .name("Usuário Convidado")
                .nickname("convidado")
                .email("convidado@email.com")
                .build();
        profile = Profile.builder()
                .id(UUID.randomUUID())
                .name("Perfil Compartilhado")
                .user(owner)
                .build();
        sharing = Sharing.builder()
                .id(UUID.randomUUID())
                .owner(owner)
                .guest(guest)
                .profile(profile)
                .build();
        sharingId = sharing.getId();
    }

    @Test
    public void testFindById_ShouldReturnSharingWhenFound() {
        when(sharingRepository.findById(sharingId)).thenReturn(Optional.of(sharing));

        Sharing result = sharingService.findById(sharingId);

        assertEquals(sharing, result);
    }

    @Test
    public void testFindById_ShouldThrowSharingNotFoundExceptionWhenNotFound() {
        when(sharingRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(SharingNotFoundException.class, () -> sharingService.findById(sharingId));
    }

    @Test
    public void testFindByProfileIdAndGuestId_ShouldReturnProfileWhenFound() {
        when(sharingRepository.findByProfileIdAndGuestId(profile.getId(), guest.getId())).thenReturn(Optional.of(sharing));

        Profile result = sharingService.findByProfileIdAndGuestId(profile.getId(), guest.getId());

        assertEquals(profile, result);
    }

    @Test
    public void testFindByProfileIdAndGuestId_ShouldThrowProfileNotFoundExceptionWhenNotFound() {
        when(sharingRepository.findByProfileIdAndGuestId(any(UUID.class), any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ProfileNotFoundException.class,
                () -> sharingService.findByProfileIdAndGuestId(profile.getId(), guest.getId()));
    }

    @Test
    public void testSave_ShouldSaveSharing() {
        when(sharingRepository.save(any(Sharing.class))).thenReturn(sharing);

        Sharing result = sharingService.save(sharing);

        assertEquals(sharing, result);
    }

    @Test
    public void testDelete_ShouldDeleteSharing() {
        when(sharingRepository.findById(sharingId)).thenReturn(Optional.of(sharing));

        sharingService.delete(owner, sharingId);

        // Verificar se o método delete do repositório foi chamado
    }

    @Test
    public void testDelete_ShouldThrowSharingNotFoundExceptionWhenSharingNotFound() {
        when(sharingRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(SharingNotFoundException.class, () -> sharingService.delete(owner, sharingId));
    }

    @Test
    public void testDelete_ShouldThrowSharingNotFoundExceptionWhenGuestTriesToDelete() {
        when(sharingRepository.findById(sharingId)).thenReturn(Optional.of(sharing));

        assertThrows(SharingNotFoundException.class, () -> sharingService.delete(guest, sharingId));
    }

    @Test
    public void testFindAllProfilesByGuestId_ShouldReturnListOfProfiles() {
        List<Sharing> sharings = List.of(sharing);
        when(sharingRepository.findAllProfilesByGuestId(guest.getId())).thenReturn(sharings);

        List<Profile> result = sharingService.findAllProfilesByGuestId(guest.getId());

        assertEquals(sharings.stream().map(Sharing::getProfile).toList(), result);
    }

    @Test
    public void testFindByProfileNameAndGuestId_ShouldReturnProfileWhenFound() {
        when(sharingRepository.findByProfileNameAndGuestId(profile.getName(), guest.getId())).thenReturn(Optional.of(sharing));

        Profile result = sharingService.findByProfileNameAndGuestId(profile.getName(), guest.getId());

        assertEquals(profile, result);
    }

    @Test
    public void testFindByProfileNameAndGuestId_ShouldThrowProfileNotFoundExceptionWhenNotFound() {
        when(sharingRepository.findByProfileNameAndGuestId(any(String.class), any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ProfileNotFoundException.class,
                () -> sharingService.findByProfileNameAndGuestId(profile.getName(), guest.getId()));
    }
}