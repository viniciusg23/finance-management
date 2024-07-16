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

import com.finapp.financemanagement.domain.dto.profile.GetAllProfileCategoriesResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.GetAllProfileTransactionsResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.GetAllProfileWalletsResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.GetProfileResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.SaveProfileRequestDTO;
import com.finapp.financemanagement.domain.dto.profile.SaveProfileResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.UpdateProfileRequestDTO;
import com.finapp.financemanagement.domain.dto.profile.UpdateProfileResponseDTO;
import com.finapp.financemanagement.domain.enums.TransactionType;
import com.finapp.financemanagement.domain.exception.profile.ProfileNameAlreadyExistsException;
import com.finapp.financemanagement.domain.exception.profile.ProfileNotFoundException;
import com.finapp.financemanagement.domain.model.Category;
import com.finapp.financemanagement.domain.model.Icon;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.Transaction;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.model.Wallet;
import com.finapp.financemanagement.domain.repository.IProfileRepository;
import com.finapp.financemanagement.domain.service.ProfileService;
import com.finapp.financemanagement.domain.service.SharingService;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;

public class ProfileServiceTest {

    @InjectMocks
    private ProfileService profileService;

    @Mock
    private IProfileRepository profileRepository;

    @Mock
    private SharingService sharingService;

    private User authenticatedUser;
    private Profile profile;
    private UUID profileId;
    private Icon icon;
    private Wallet wallet;
    private Category category;
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        authenticatedUser = User.builder()
                .id(UUID.randomUUID())
                .name("Usuário de Teste")
                .nickname("usuarioTeste")
                .email("usuario@teste.com")
                .build();
        profile = Profile.builder()
                .id(UUID.randomUUID())
                .name("Perfil de Teste")
                .user(authenticatedUser)
                .createdAt(LocalDateTime.now())
                .build();
        profileId = profile.getId();
        icon = Icon.builder()
                .id(UUID.randomUUID())
                .name("Ícone de Teste")
                .thirdPartyId("thirdPartyId")
                .createdAt(LocalDateTime.now())
                .build();
        wallet = Wallet.builder()
                .id(UUID.randomUUID())
                .name("Carteira de Teste")
                .description("Descrição da Carteira")
                .color("Azul")
                .goalWallet(false)
                .balance(100.0)
                .icon(icon)
                .user(authenticatedUser)
                .profile(profile)
                .createdAt(LocalDateTime.now())
                .build();
        category = Category.builder()
                .id(UUID.randomUUID())
                .name("Categoria de Teste")
                .icon(icon)
                .type(TransactionType.CREDIT)
                .profile(profile)
                .createdAt(LocalDateTime.now())
                .build();
        transaction = Transaction.builder()
                .id(UUID.randomUUID())
                .wallet(wallet)
                .category(category)
                .profile(profile)
                .user(authenticatedUser)
                .title("Transação de Teste")
                .commentary("Comentário da Transação")
                .transactionValue(50.0)
                .date(java.time.LocalDate.now())
                .type(TransactionType.CREDIT)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    public void testFindById_ShouldReturnProfileWhenFound() {
        when(profileRepository.findById(profileId)).thenReturn(Optional.of(profile));

        Profile result = profileService.findById(profileId);

        assertEquals(profile, result);
    }

    @Test
    public void testFindById_ShouldThrowProfileNotFoundExceptionWhenNotFound() {
        when(profileRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ProfileNotFoundException.class, () -> profileService.findById(profileId));
    }

    @Test
    public void testFindByIdAndUserId_ShouldReturnProfileWhenFound() {
        when(profileRepository.findByIdAndUserId(profileId, authenticatedUser.getId())).thenReturn(Optional.of(profile));

        Profile result = profileService.findByIdAndUserId(profileId, authenticatedUser.getId());

        assertEquals(profile, result);
    }

    @Test
    public void testFindByIdAndUserId_ShouldReturnProfileFromSharingServiceWhenNotFoundInRepository() {
        when(profileRepository.findByIdAndUserId(any(UUID.class), any(UUID.class))).thenReturn(Optional.empty());
        when(sharingService.findByProfileIdAndGuestId(profileId, authenticatedUser.getId())).thenReturn(profile);

        Profile result = profileService.findByIdAndUserId(profileId, authenticatedUser.getId());

        assertEquals(profile, result);
    }

    @Test
    public void testFindByIdAndUserId_ShouldThrowProfileNotFoundExceptionWhenNotFoundInRepositoryAndSharingService() {
        when(profileRepository.findByIdAndUserId(any(UUID.class), any(UUID.class))).thenReturn(Optional.empty());
        when(sharingService.findByProfileIdAndGuestId(any(UUID.class), any(UUID.class))).thenThrow(ProfileNotFoundException.class);

        assertThrows(ProfileNotFoundException.class,
                () -> profileService.findByIdAndUserId(profileId, authenticatedUser.getId()));
    }

    @Test
    public void testFindByNameAndUserId_ShouldReturnProfileWhenFound() {
        when(profileRepository.findByNameAndUserId(profile.getName(), authenticatedUser.getId())).thenReturn(Optional.of(profile));

        Profile result = profileService.findByNameAndUserId(profile.getName(), authenticatedUser.getId());

        assertEquals(profile, result);
    }

    @Test
    public void testFindByNameAndUserId_ShouldReturnProfileFromSharingServiceWhenNotFoundInRepository() {
        when(profileRepository.findByNameAndUserId(any(String.class), any(UUID.class))).thenReturn(Optional.empty());
        when(sharingService.findByProfileNameAndGuestId(profile.getName(), authenticatedUser.getId())).thenReturn(profile);

        Profile result = profileService.findByNameAndUserId(profile.getName(), authenticatedUser.getId());

        assertEquals(profile, result);
    }

    @Test
    public void testGet_ShouldReturnGetProfileResponseDTOWhenFound() {
        when(profileRepository.findById(profileId)).thenReturn(Optional.of(profile));

        GetProfileResponseDTO result = profileService.get(profileId);

        assertEquals(new GetProfileResponseDTO(profile), result);
    }

    @Test
    public void testGetAllByUserId_ShouldReturnListOfProfilesWhenFound() {
        List<Profile> profiles = List.of(profile);
        when(profileRepository.findAllByUserId(authenticatedUser.getId())).thenReturn(profiles);

        List<Profile> result = profileService.findAllByUserId(authenticatedUser.getId());

        assertEquals(profiles, result);
    }

    @Test
    public void testGetAllCategories_ShouldReturnGetAllProfileCategoriesResponseDTO() {
        List<Category> categories = List.of(category);
        profile.setCategories(categories);
        when(profileRepository.findByIdAndUserId(profileId, authenticatedUser.getId())).thenReturn(Optional.of(profile));

        GetAllProfileCategoriesResponseDTO result = profileService.getAllCategories(authenticatedUser, profileId);

        assertEquals(new GetAllProfileCategoriesResponseDTO(profile), result);
    }

    @Test
    public void testGetAllWallets_ShouldReturnGetAllProfileWalletsResponseDTO() {
        List<Wallet> wallets = List.of(wallet);
        profile.setWallets(wallets);
        when(profileRepository.findByIdAndUserId(profileId, authenticatedUser.getId())).thenReturn(Optional.of(profile));

        GetAllProfileWalletsResponseDTO result = profileService.getAllWallets(authenticatedUser, profileId);

        assertEquals(new GetAllProfileWalletsResponseDTO(profile), result);
    }

    @Test
    public void testGetAllTransactions_ShouldReturnGetAllProfileTransactionsResponseDTO() {
        List<Transaction> transactions = List.of(transaction);
        profile.setTransactions(transactions);
        when(profileRepository.findByIdAndUserId(profileId, authenticatedUser.getId())).thenReturn(Optional.of(profile));

        GetAllProfileTransactionsResponseDTO result = profileService.getAllTransactions(authenticatedUser, profileId);

        assertEquals(new GetAllProfileTransactionsResponseDTO(profile), result);
    }

    @Test
    public void testSave_ShouldSaveProfileAndReturnSaveProfileResponseDTO() {
        SaveProfileRequestDTO saveProfileRequestDTO = new SaveProfileRequestDTO("Novo Perfil");

        when(profileRepository.findByNameAndUserId(saveProfileRequestDTO.name(), authenticatedUser.getId()))
                .thenReturn(Optional.empty());
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        SaveProfileResponseDTO result = profileService.save(authenticatedUser, saveProfileRequestDTO);

        assertEquals(new SaveProfileResponseDTO(profile), result);
    }

    @Test
    public void testSave_ShouldThrowProfileNameAlreadyExistsExceptionWhenNameAlreadyExists() {
        SaveProfileRequestDTO saveProfileRequestDTO = new SaveProfileRequestDTO("Perfil de Teste");

        when(profileRepository.findByNameAndUserId(saveProfileRequestDTO.name(), authenticatedUser.getId()))
                .thenReturn(Optional.of(profile));

        assertThrows(ProfileNameAlreadyExistsException.class,
                () -> profileService.save(authenticatedUser, saveProfileRequestDTO));
    }

    @Test
    public void testUpdate_ShouldUpdateProfileAndReturnUpdateProfileResponseDTO() {
        UpdateProfileRequestDTO updateProfileRequestDTO = new UpdateProfileRequestDTO(profile.getId().toString(),
                "Perfil Atualizado");

        when(profileRepository.findByIdAndUserId(UUIDUtils.stringToUUID(updateProfileRequestDTO.id()),
                authenticatedUser.getId())).thenReturn(Optional.of(profile));
        when(profileRepository.findByNameAndUserId(updateProfileRequestDTO.name(), authenticatedUser.getId()))
                .thenReturn(Optional.empty());
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        UpdateProfileResponseDTO result = profileService.update(authenticatedUser, updateProfileRequestDTO);

        assertEquals(new UpdateProfileResponseDTO(profile), result);
    }

    @Test
    public void testUpdate_ShouldThrowProfileNameAlreadyExistsExceptionWhenNameAlreadyExists() {
        UpdateProfileRequestDTO updateProfileRequestDTO = new UpdateProfileRequestDTO(profile.getId().toString(),
                "Perfil de Teste");

        when(profileRepository.findByIdAndUserId(UUIDUtils.stringToUUID(updateProfileRequestDTO.id()),
                authenticatedUser.getId())).thenReturn(Optional.of(profile));
        when(profileRepository.findByNameAndUserId(updateProfileRequestDTO.name(), authenticatedUser.getId()))
                .thenReturn(Optional.of(profile));

        assertThrows(ProfileNameAlreadyExistsException.class,
                () -> profileService.update(authenticatedUser, updateProfileRequestDTO));
    }

    @Test
    public void testDelete_ShouldDeleteProfileWhenFound() {
        when(profileRepository.findByIdAndUserId(profileId, authenticatedUser.getId())).thenReturn(Optional.of(profile));

        profileService.delete(authenticatedUser, profileId);
    }

}