package com.finapp.financemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.finapp.financemanagement.domain.enums.TransactionType;
import com.finapp.financemanagement.domain.exception.transaction.TransactionNotFoundException;
import com.finapp.financemanagement.domain.model.Category;
import com.finapp.financemanagement.domain.model.Icon;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.Transaction;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.model.Wallet;
import com.finapp.financemanagement.domain.repository.ITransactionRepository;
import com.finapp.financemanagement.domain.service.CategoryService;
import com.finapp.financemanagement.domain.service.ProfileService;
import com.finapp.financemanagement.domain.service.TransactionService;
import com.finapp.financemanagement.domain.service.WalletService;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private ITransactionRepository transactionRepository;

    @Mock
    private ProfileService profileService;

    @Mock
    private WalletService walletService;

    @Mock
    private CategoryService categoryService;

    private User authenticatedUser;
    private Profile profile;
    private Wallet wallet;
    private Category category;
    private Transaction transaction;
    private UUID transactionId;

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
                .build();
        wallet = Wallet.builder()
                .id(UUID.randomUUID())
                .name("Carteira de Teste")
                .description("Descrição da Carteira")
                .color("Azul")
                .goalWallet(false)
                .balance(100.0)
                .user(authenticatedUser)
                .profile(profile)
                .createdAt(LocalDateTime.now())
                .build();
        category = Category.builder()
                .id(UUID.randomUUID())
                .name("Categoria de Teste")
                .icon(Icon.builder().id(UUID.randomUUID()).build())
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
                .date(LocalDate.now())
                .type(TransactionType.CREDIT)
                .createdAt(LocalDateTime.now())
                .build();
        transactionId = transaction.getId();
    }

    @Test
    public void testFindById_ShouldReturnTransactionWhenFound() {
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.findById(transactionId);

        assertEquals(transaction, result);
    }

    @Test
    public void testFindById_ShouldThrowTransactionNotFoundExceptionWhenNotFound() {
        when(transactionRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> transactionService.findById(transactionId));
    }

    @Test
    public void testGet_ShouldThrowTransactionNotFoundExceptionWhenNotFound() {
        when(transactionRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> transactionService.get(authenticatedUser, transactionId));
    }


    @Test
    public void testDelete_ShouldDeleteTransactionWhenFound() {
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        when(profileService.findByIdAndUserId(profile.getId(), authenticatedUser.getId())).thenReturn(profile);

        transactionService.delete(authenticatedUser, transactionId);
    }

    @Test
    public void testDelete_ShouldThrowTransactionNotFoundExceptionWhenNotFound() {
        when(transactionRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> transactionService.delete(authenticatedUser, transactionId));
    }
}