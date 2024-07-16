package com.finapp.financemanagement.domain.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finapp.financemanagement.domain.dto.transaction.GetTransactionResponseDTO;
import com.finapp.financemanagement.domain.dto.transaction.SaveTransactionRequestDTO;
import com.finapp.financemanagement.domain.dto.transaction.SaveTransactionResponseDTO;
import com.finapp.financemanagement.domain.dto.transaction.UpdateTransactionRequestDTO;
import com.finapp.financemanagement.domain.dto.transaction.UpdateTransactionResponseDTO;
import com.finapp.financemanagement.domain.enums.TransactionType;
import com.finapp.financemanagement.domain.exception.profile.ProfileNotFoundException;
import com.finapp.financemanagement.domain.exception.transaction.TransactionNotFoundException;
import com.finapp.financemanagement.domain.model.Category;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.Transaction;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.model.Wallet;
import com.finapp.financemanagement.domain.repository.ITransactionRepository;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;

import jakarta.transaction.Transactional;

/**
 * Serviço para gerenciamento de transações.
*/
@Service
public class TransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Busca uma transação pelo seu identificador.
     * @param id Identificador UUID da transação.
     * @return A transação encontrada.
     * @throws TransactionNotFoundException se a transação não for encontrada.
    */
    public Transaction findById(UUID id){
        Optional<Transaction> optionalTransaction = this.transactionRepository.findById(id);

        if(optionalTransaction.isEmpty()){
            throw new TransactionNotFoundException();
        }

        return optionalTransaction.get();
    }

    /**
     * Busca uma transação pelo identificador da transação e pelo identificador do perfil.
     * @param id Identificador UUID da transação.
     * @param profileId Identificador UUID do perfil associado.
     * @return A transação encontrada.
     * @throws TransactionNotFoundException se a transação não for encontrada.
    */
    public GetTransactionResponseDTO get(User authenticatedUser, UUID id){
        try {
            Transaction transaction = this.findById(id);
            this.profileService.findByIdAndUserId(transaction.getProfile().getId(), authenticatedUser.getId());
            return new GetTransactionResponseDTO(transaction);
        } catch (ProfileNotFoundException exception) {
            throw new TransactionNotFoundException();
        }
    }
    
    /**
     * Reverte uma transação.
     * @param transaction A transação a ser revertida.
    */
    public void revertTransaction(Transaction transaction){
        // FIXME: contra o principio de aberto e fechado
        if(transaction.getType().equals(TransactionType.CREDIT)){
            this.walletService.debit(transaction.getWallet(), transaction.getTransactionValue());
        }
        else{
            this.walletService.credit(transaction.getWallet(), transaction.getTransactionValue());
        }
    }

    /**
     * Cria uma nova transação de crédito.
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com os dados da transação.
     * @return DTO de resposta com os detalhes da transação criada.
    */
    @Transactional
    public SaveTransactionResponseDTO credit(User authenticatedUser, SaveTransactionRequestDTO data){
        Wallet wallet = this.walletService.findById(UUIDUtils.stringToUUID(data.walletId()));
        Profile profile = this.profileService.findByIdAndUserId(wallet.getProfile().getId(), authenticatedUser.getId());
        Category category = this.categoryService.findByIdAndProfileId(UUIDUtils.stringToUUID(data.categoryId()), profile.getId());

        Transaction transaction = Transaction.builder()
            .profile(profile)
            .wallet(wallet)
            .category(category)
            .user(authenticatedUser)
            .title(data.title())
            .commentary(data.commentary())
            .transactionValue(data.value())
            .date(data.date())
            .type(TransactionType.CREDIT)
            .build();

        this.walletService.credit(wallet, transaction.getTransactionValue());
        Transaction savedTransaction = this.transactionRepository.save(transaction);
        return new SaveTransactionResponseDTO(savedTransaction);
    }
    
    /**
     * Cria uma nova transação de débito.
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com os dados da transação.
     * @return DTO de resposta com os detalhes da transação criada.
    */
    @Transactional
    public SaveTransactionResponseDTO debit(User authenticatedUser, SaveTransactionRequestDTO data) {
        Wallet wallet = this.walletService.findById(UUIDUtils.stringToUUID(data.walletId()));
        Profile profile = this.profileService.findByIdAndUserId(wallet.getProfile().getId(), authenticatedUser.getId());
        Category category = this.categoryService.findByIdAndProfileId(UUIDUtils.stringToUUID(data.categoryId()), profile.getId());

        Transaction transaction = Transaction.builder()
                .profile(profile)
                .wallet(wallet)
                .category(category)
                .user(authenticatedUser)
                .title(data.title())
                .commentary(data.commentary())
                .transactionValue(data.value())
                .date(data.date())
                .type(TransactionType.DEBIT)
                .build();

        this.walletService.debit(wallet, transaction.getTransactionValue());
        Transaction savedTransaction = this.transactionRepository.save(transaction);
        return new SaveTransactionResponseDTO(savedTransaction);
    }
    
    /**
     * Atualiza uma transação existente.
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com os dados da transação.
     * @return DTO de resposta com os detalhes da transação atualizada.
     * @throws TransactionNotFoundException se a transação não for encontrada.
     * @throws ProfileNotFoundException se o perfil não for encontrado.
     * @throws CategoryNotFoundException se a categoria não for encontrada.
    */
    @Transactional
    public UpdateTransactionResponseDTO update(User authenticatedUser, UpdateTransactionRequestDTO data) {
        Transaction transaction = this.findById(UUIDUtils.stringToUUID(data.id()));
        Profile profile = this.profileService.findByIdAndUserId(transaction.getProfile().getId(), authenticatedUser.getId());
        Category category = this.categoryService.findByIdAndProfileId(UUIDUtils.stringToUUID(data.categoryId()), profile.getId());

        Double actualValue = transaction.getTransactionValue();

        transaction.setCategory(category);
        transaction.setTitle(data.title());
        transaction.setCommentary(data.commentary());
        transaction.setTransactionValue(data.value());
        transaction.setDate(data.date());

        //FIXME: contra o principio de aberto e fechado
        if(transaction.getType().equals(TransactionType.CREDIT)){
            this.walletService.debit(transaction.getWallet(), actualValue);
            this.walletService.credit(transaction.getWallet(), transaction.getTransactionValue());
        }
        else{
            this.walletService.credit(transaction.getWallet(), actualValue);
            this.walletService.debit(transaction.getWallet(), transaction.getTransactionValue());
        }

        Transaction savedTransaction = this.transactionRepository.save(transaction);
        return new UpdateTransactionResponseDTO(savedTransaction);
    }

    /**
     * Deleta uma transação do banco de dados.
     * @param authenticatedUser Usuário autenticado.
     * @param id Identificador UUID da transação.
     * @throws TransactionNotFoundException se a transação não for encontrada.
     * @throws ProfileNotFoundException se o perfil não for encontrado.
    */
    @Transactional
    public void delete(User authenticatedUser, UUID id){
        try {
            Transaction transaction = this.findById(id);
            this.profileService.findByIdAndUserId(transaction.getProfile().getId(), authenticatedUser.getId());
            this.revertTransaction(transaction);
            this.transactionRepository.delete(transaction);
        } catch (ProfileNotFoundException exception) {
            throw new TransactionNotFoundException();
        }
    }

    

}
