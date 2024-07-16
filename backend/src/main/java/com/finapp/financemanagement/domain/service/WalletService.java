package com.finapp.financemanagement.domain.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finapp.financemanagement.domain.dto.wallet.GetWalletResponseDTO;
import com.finapp.financemanagement.domain.dto.wallet.SaveWalletRequestDTO;
import com.finapp.financemanagement.domain.dto.wallet.SaveWalletResponseDTO;
import com.finapp.financemanagement.domain.dto.wallet.UpdateWalletRequestDTO;
import com.finapp.financemanagement.domain.dto.wallet.UpdateWalletResponseDTO;
import com.finapp.financemanagement.domain.exception.profile.ProfileNotFoundException;
import com.finapp.financemanagement.domain.exception.wallet.WalletNameAlreadyExistsException;
import com.finapp.financemanagement.domain.exception.wallet.WalletNotFoundException;
import com.finapp.financemanagement.domain.model.Icon;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.model.Wallet;
import com.finapp.financemanagement.domain.repository.IWalletRepository;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;

import jakarta.transaction.Transactional;

/**
 * Serviço para gerenciamento de carteiras.
*/
@Service
public class WalletService {

    @Autowired
    private IWalletRepository walletRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private IconService iconService;

    /**
     * Busca uma carteira pelo seu identificador.
     * @param id Identificador UUID da carteira.
     * @return A carteira encontrada.
     * @throws WalletNotFoundException se a carteira não for encontrada.
    */
    public Wallet findById(UUID id) throws WalletNotFoundException {
        Optional<Wallet> optionalWallet = this.walletRepository.findById(id);

        if (optionalWallet.isEmpty()) {
            throw new WalletNotFoundException();
        }

        return optionalWallet.get();
    }

    /**
     * Busca uma carteira pelo seu identificador e pelo identificador do usuário.
     * @param id Identificador UUID da carteira.
     * @param userId Identificador UUID do usuário associado.
     * @return A carteira encontrada.
     * @throws WalletNotFoundException se a carteira não for encontrada.
    */
    public Wallet findByIdAndUserId(UUID id, UUID userId) throws WalletNotFoundException {
        Optional<Wallet> optionalWallet = this.walletRepository.findByIdAndUserId(id, userId);

        if (optionalWallet.isEmpty()) {
            throw new WalletNotFoundException();
        }

        return optionalWallet.get();
    }

    /**
     * Retorna os detalhes de uma carteira para um usuário autenticado.
     * @param authenticatedUser Usuário autenticado.
     * @param id Identificador UUID da carteira.
     * @return DTO com os detalhes da carteira.
     * @throws WalletNotFoundException se a carteira não for encontrada.
    */
    public GetWalletResponseDTO get(UUID id) {
        Wallet wallet = this.findById(id);
        return new GetWalletResponseDTO(wallet);
    }

    /**
     * Credita um valor em uma carteira.
     * @param wallet A carteira a ser creditada.
     * @param value O valor a ser creditado.
    */
    @Transactional
    public void credit(Wallet wallet, Double value){
        wallet.setBalance(wallet.getBalance() + value);
        this.walletRepository.save(wallet);
    }

    /**
     * Debita um valor em uma carteira.
     * @param wallet A carteira a ser debitada.
     * @param value O valor a ser debitado.
    */
    @Transactional
    public void debit(Wallet wallet, Double value) {
        wallet.setBalance(wallet.getBalance() - value);
        this.walletRepository.save(wallet);
    }

    /**
     * Salva uma nova carteira no sistema.
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com os dados da carteira.
     * @return DTO de resposta com os detalhes da carteira criada.
    */
    @Transactional
    public SaveWalletResponseDTO save(User authenticatedUser, SaveWalletRequestDTO data) {
        Profile profile = this.profileService.findByIdAndUserId(UUIDUtils.stringToUUID(data.profileId()), authenticatedUser.getId());

        if (this.walletRepository.findByNameAndProfileId(data.name(), profile.getId()).isPresent()) {
            throw new WalletNameAlreadyExistsException();
        }

        User user = this.userService.findById(authenticatedUser.getId());
        Icon icon = this.iconService.findById(UUIDUtils.stringToUUID(data.iconId()));

        Wallet wallet = Wallet.builder()
                .name(data.name())
                .description(data.description())
                .color(data.color())
                .goalWallet(data.goalWallet())
                .user(user)
                .profile(profile)
                .icon(icon)
                .balance(0.0)
                .build();

        Wallet savedWallet = this.walletRepository.save(wallet);
        return new SaveWalletResponseDTO(savedWallet);
    }

    /**
     * Atualiza uma carteira existente.
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com informações para atualizar a carteira.
     * @return DTO de resposta com os detalhes da carteira atualizada.
     * @throws WalletNameAlreadyExistsException se o nome da carteira já existir.
    */
    @Transactional
    public UpdateWalletResponseDTO update(User authenticatedUser, UpdateWalletRequestDTO data) throws WalletNameAlreadyExistsException {
        try {
            Wallet wallet = this.findById(UUIDUtils.stringToUUID(data.id()));
            this.profileService.findByIdAndUserId(wallet.getProfile().getId(), authenticatedUser.getId());

            if (this.walletRepository.findByNameAndProfileId(data.name(), wallet.getProfile().getId()).isPresent()) {
                throw new WalletNameAlreadyExistsException();
            }

            wallet.setName(data.name());
            wallet.setDescription(data.description());
            wallet.setColor(data.color());
            wallet.setGoalWallet(data.goalWallet());
            wallet.setBalance(data.balance());
            wallet.setIcon(this.iconService.findById(UUIDUtils.stringToUUID(data.iconId())));

            Wallet updatedWallet = this.walletRepository.save(wallet);
            return new UpdateWalletResponseDTO(updatedWallet);

        } catch (ProfileNotFoundException exception) {
            throw new WalletNotFoundException();
        }
    }

    /**
     * Exclui uma carteira do sistema.
     * @param authenticatedUser Usuário autenticado.
     * @param id Identificador UUID da carteira a ser excluída.
    */
    @Transactional
    public void delete(User authenticatedUser, UUID id) {
        try {
            Wallet wallet = this.findById(id);
            this.profileService.findByIdAndUserId(wallet.getProfile().getId(), authenticatedUser.getId());
            this.walletRepository.deleteById(wallet.getId());
        } catch (ProfileNotFoundException exception) {
            throw new WalletNotFoundException();
        } 
    }
}
