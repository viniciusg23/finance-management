package com.finapp.financemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.finapp.financemanagement.domain.dto.wallet.GetWalletResponseDTO;
import com.finapp.financemanagement.domain.dto.wallet.SaveWalletRequestDTO;
import com.finapp.financemanagement.domain.dto.wallet.SaveWalletResponseDTO;
import com.finapp.financemanagement.domain.dto.wallet.UpdateWalletRequestDTO;
import com.finapp.financemanagement.domain.dto.wallet.UpdateWalletResponseDTO;
import com.finapp.financemanagement.domain.exception.wallet.WalletNameAlreadyExistsException;
import com.finapp.financemanagement.domain.exception.wallet.WalletNotFoundException;
import com.finapp.financemanagement.domain.model.Icon;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.model.Wallet;
import com.finapp.financemanagement.domain.repository.IWalletRepository;
import com.finapp.financemanagement.domain.service.IconService;
import com.finapp.financemanagement.domain.service.ProfileService;
import com.finapp.financemanagement.domain.service.UserService;
import com.finapp.financemanagement.domain.service.WalletService;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;

public class WalletServiceTest {

    @InjectMocks
    private WalletService walletService;

    @Mock
    private IWalletRepository walletRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProfileService profileService;

    @Mock
    private IconService iconService;

    private User authenticatedUser;
    private Profile profile;
    private Icon icon;
    private Wallet wallet;
    private UUID walletId;

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
        walletId = wallet.getId();
    }

    @Test
    public void testFindById_ShouldReturnWalletWhenFound() {
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

        Wallet result = walletService.findById(walletId);

        assertEquals(wallet, result);
    }

    @Test
    public void testFindById_ShouldThrowWalletNotFoundExceptionWhenNotFound() {
        when(walletRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class, () -> walletService.findById(walletId));
    }

    @Test
    public void testFindByIdAndUserId_ShouldReturnWalletWhenFound() {
        when(walletRepository.findByIdAndUserId(walletId, authenticatedUser.getId())).thenReturn(Optional.of(wallet));

        Wallet result = walletService.findByIdAndUserId(walletId, authenticatedUser.getId());

        assertEquals(wallet, result);
    }

    @Test
    public void testFindByIdAndUserId_ShouldThrowWalletNotFoundExceptionWhenNotFound() {
        when(walletRepository.findByIdAndUserId(any(UUID.class), any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class,
                () -> walletService.findByIdAndUserId(walletId, authenticatedUser.getId()));
    }

    @Test
    public void testGet_ShouldReturnGetWalletResponseDTOWhenFound() {
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

        GetWalletResponseDTO result = walletService.get(walletId);

        assertEquals(new GetWalletResponseDTO(wallet), result);
    }

    @Test
    public void testCredit_ShouldIncreaseWalletBalance() {
        double amountToCredit = 50.0;
        double expectedBalance = wallet.getBalance() + amountToCredit;

        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        walletService.credit(wallet, amountToCredit);

        assertEquals(expectedBalance, wallet.getBalance());
    }

    @Test
    public void testDebit_ShouldDecreaseWalletBalance() {
        double amountToDebit = 25.0;
        double expectedBalance = wallet.getBalance() - amountToDebit;

        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        walletService.debit(wallet, amountToDebit);

        assertEquals(expectedBalance, wallet.getBalance());
    }

    @Test
    public void testSave_ShouldSaveWalletAndReturnSaveWalletResponseDTO() {
        SaveWalletRequestDTO saveWalletRequestDTO = new SaveWalletRequestDTO(profile.getId().toString(),
                icon.getId().toString(), "Nova Carteira", "Descrição da Nova Carteira", "Verde", false);

        when(profileService.findByIdAndUserId(UUIDUtils.stringToUUID(saveWalletRequestDTO.profileId()),
                authenticatedUser.getId())).thenReturn(profile);
        when(iconService.findById(UUIDUtils.stringToUUID(saveWalletRequestDTO.iconId()))).thenReturn(icon);
        when(userService.findById(authenticatedUser.getId())).thenReturn(authenticatedUser);
        when(walletRepository.findByNameAndProfileId(saveWalletRequestDTO.name(), profile.getId()))
                .thenReturn(Optional.empty());
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        SaveWalletResponseDTO result = walletService.save(authenticatedUser, saveWalletRequestDTO);

        assertEquals(new SaveWalletResponseDTO(wallet), result);
    }

    @Test
    public void testSave_ShouldThrowWalletNameAlreadyExistsExceptionWhenNameAlreadyExists() {
        SaveWalletRequestDTO saveWalletRequestDTO = new SaveWalletRequestDTO(profile.getId().toString(),
                icon.getId().toString(), "Carteira de Teste", "Descrição da Nova Carteira", "Verde", false);

        when(profileService.findByIdAndUserId(UUIDUtils.stringToUUID(saveWalletRequestDTO.profileId()),
                authenticatedUser.getId())).thenReturn(profile);
        when(iconService.findById(UUIDUtils.stringToUUID(saveWalletRequestDTO.iconId()))).thenReturn(icon);
        when(userService.findById(authenticatedUser.getId())).thenReturn(authenticatedUser);
        when(walletRepository.findByNameAndProfileId(saveWalletRequestDTO.name(), profile.getId()))
                .thenReturn(Optional.of(wallet));

        assertThrows(WalletNameAlreadyExistsException.class,
                () -> walletService.save(authenticatedUser, saveWalletRequestDTO));
    }

    @Test
    public void testUpdate_ShouldUpdateWalletAndReturnUpdateWalletResponseDTO() {
        UpdateWalletRequestDTO updateWalletRequestDTO = new UpdateWalletRequestDTO(wallet.getId().toString(),
                icon.getId().toString(), "Carteira Atualizada", "Nova Descrição", "Amarelo", true, 150.0);

        when(walletRepository.findById(UUIDUtils.stringToUUID(updateWalletRequestDTO.id()))).thenReturn(Optional.of(wallet));
        when(profileService.findByIdAndUserId(profile.getId(), authenticatedUser.getId())).thenReturn(profile);
        when(iconService.findById(UUIDUtils.stringToUUID(updateWalletRequestDTO.iconId()))).thenReturn(icon);
        when(walletRepository.findByNameAndProfileId(updateWalletRequestDTO.name(), profile.getId()))
                .thenReturn(Optional.empty());
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        UpdateWalletResponseDTO result = walletService.update(authenticatedUser, updateWalletRequestDTO);

        assertEquals(new UpdateWalletResponseDTO(wallet), result);
    }

    @Test
    public void testUpdate_ShouldThrowWalletNameAlreadyExistsExceptionWhenNameAlreadyExists() {
        UpdateWalletRequestDTO updateWalletRequestDTO = new UpdateWalletRequestDTO(wallet.getId().toString(),
                icon.getId().toString(), "Carteira de Teste", "Nova Descrição", "Amarelo", true, 150.0);

        when(walletRepository.findById(UUIDUtils.stringToUUID(updateWalletRequestDTO.id()))).thenReturn(Optional.of(wallet));
        when(profileService.findByIdAndUserId(profile.getId(), authenticatedUser.getId())).thenReturn(profile);
        when(iconService.findById(UUIDUtils.stringToUUID(updateWalletRequestDTO.iconId()))).thenReturn(icon);
        when(walletRepository.findByNameAndProfileId(updateWalletRequestDTO.name(), profile.getId()))
                .thenReturn(Optional.of(wallet));

        assertThrows(WalletNameAlreadyExistsException.class,
                () -> walletService.update(authenticatedUser, updateWalletRequestDTO));
    }

    @Test
    public void testDelete_ShouldDeleteWalletWhenFound() {
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));
        when(profileService.findByIdAndUserId(profile.getId(), authenticatedUser.getId())).thenReturn(profile);

        walletService.delete(authenticatedUser, walletId);
    }

    @Test
    public void testDelete_ShouldThrowWalletNotFoundExceptionWhenNotFound() {
        when(walletRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class, () -> walletService.delete(authenticatedUser, walletId));
    }
}