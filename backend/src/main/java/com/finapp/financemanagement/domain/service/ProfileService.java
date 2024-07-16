package com.finapp.financemanagement.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finapp.financemanagement.domain.dto.profile.GetAllProfileCategoriesResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.GetAllProfileTransactionsResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.GetAllProfileWalletsResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.GetProfileResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.SaveProfileRequestDTO;
import com.finapp.financemanagement.domain.dto.profile.SaveProfileResponseDTO;
import com.finapp.financemanagement.domain.dto.profile.UpdateProfileRequestDTO;
import com.finapp.financemanagement.domain.dto.profile.UpdateProfileResponseDTO;
import com.finapp.financemanagement.domain.exception.profile.ProfileNameAlreadyExistsException;
import com.finapp.financemanagement.domain.exception.profile.ProfileNotFoundException;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.IProfileRepository;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;

import jakarta.transaction.Transactional;
/**
 * Serviço para gerenciamento de perfis.
*/
@Service
public class ProfileService {

    @Autowired
    private IProfileRepository profileRepository;

    @Autowired
    private SharingService sharingService;

    /**
     * Busca um perfil pelo seu identificador.
     * @param id Identificador UUID do perfil.
     * @return O perfil encontrado.
     * @throws ProfileNotFoundException se o perfil não for encontrado.
     */
    public Profile findById(UUID id) throws ProfileNotFoundException {
        Optional<Profile> optionalProfile = this.profileRepository.findById(id);

        if (optionalProfile.isEmpty()) {
            throw new ProfileNotFoundException();
        }

        return optionalProfile.get();
    }
    /**
     * Busca um perfil pelo identificador do perfil e pelo identificador do usuário.
     * @param id Identificador UUID do perfil.
     * @param userId Identificador UUID do usuário associado.
     * @return O perfil encontrado.
     * @throws ProfileNotFoundException se o perfil não for encontrado.
     */
    public Profile findByIdAndUserId(UUID id, UUID userId) throws ProfileNotFoundException {
        Optional<Profile> optionalProfile = this.profileRepository.findByIdAndUserId(id, userId);

        if (optionalProfile.isEmpty()) {
            return this.sharingService.findByProfileIdAndGuestId(id, userId);
        } else {
            return optionalProfile.get();
        }

    }
    /**
     * Busca um perfil pelo nome e pelo identificador do usuário.
     * @param name Nome do perfil.
     * @param userId Identificador UUID do usuário associado.
     * @return O perfil encontrado.
    */
    public Profile findByNameAndUserId(String name, UUID userId) {
        Optional<Profile> optionalProfile = this.profileRepository.findByNameAndUserId(name, userId);

        if (optionalProfile.isEmpty()) {
            return this.sharingService.findByProfileNameAndGuestId(name, userId);
        }
        
        return optionalProfile.get();
    }

    /**
     * Retorna os detalhes de um perfil.
     * @param id Identificador UUID do perfil.
     * @return DTO com os detalhes do perfil.
    */
    public GetProfileResponseDTO get(UUID id) {
        Profile profile = this.findById(id);
        return new GetProfileResponseDTO(profile);
    }
    
    /**
     * Retorna todos os perfis associados a um usuário.
     * @param id Identificador UUID do usuário.
     * @return Lista de perfis associados ao usuário.
    */
    public List<Profile> findAllByUserId(UUID id) {
        List<Profile> profiles = this.profileRepository.findAllByUserId(id);
        return profiles;
    }
    /**
     * Retorna todos os perfis associados a um usuário autenticado.
     * @param authenticatedUser Usuário autenticado.
     * @return Lista de perfis associados ao usuário autenticado.
    */
    public GetAllProfileCategoriesResponseDTO getAllCategories(User authenticatedUser, UUID profileId) {
        Profile profile = this.findByIdAndUserId(profileId, authenticatedUser.getId());
        return new GetAllProfileCategoriesResponseDTO(profile);
    }
    
    /**
     * Retorna todos os perfis associados a um usuário autenticado.
     * @param authenticatedUser Usuário autenticado.
     * @return Lista de perfis associados ao usuário autenticado.
    */
    public GetAllProfileWalletsResponseDTO getAllWallets(User authenticatedUser, UUID profileId) {
        Profile profile = this.findByIdAndUserId(profileId, authenticatedUser.getId());
        return new GetAllProfileWalletsResponseDTO(profile);
    }

    /**
     * Retorna todos os perfis associados a um usuário autenticado.
     * @param authenticatedUser Usuário autenticado.
     * @return Lista de perfis associados ao usuário autenticado.
    */
    public GetAllProfileTransactionsResponseDTO getAllTransactions(User authenticatedUser, UUID profileId) {
        Profile profile = this.findByIdAndUserId(profileId, authenticatedUser.getId());
        return new GetAllProfileTransactionsResponseDTO(profile);
    }
    /**
     * Salva um novo perfil no banco de dados.
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com informações para criar um novo perfil.
     * @return DTO de resposta com os detalhes do perfil salvo.
     * @throws ProfileNameAlreadyExistsException se o nome do perfil já existir.
    */
    @Transactional
    public SaveProfileResponseDTO save(User authenticatedUser, SaveProfileRequestDTO data)
            throws ProfileNameAlreadyExistsException {

        Optional<Profile> optionalProfile = this.profileRepository.findByNameAndUserId(data.name(),
                authenticatedUser.getId());

        if (optionalProfile.isPresent()) {
            throw new ProfileNameAlreadyExistsException();
        }

        Profile profile = Profile.builder()
                .name(data.name())
                .user(authenticatedUser)
                .build();
                
        Profile savedProfile = this.profileRepository.save(profile);
        return new SaveProfileResponseDTO(savedProfile);
    }
    
    /**
     * Atualiza um perfil existente.
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com informações para atualizar o perfil.
     * @return DTO de resposta com os detalhes do perfil atualizado.
     * @throws ProfileNameAlreadyExistsException se o nome do perfil já existir para outro perfil.
    */
    @Transactional
    public UpdateProfileResponseDTO update(User authenticatedUser, UpdateProfileRequestDTO data)
            throws ProfileNameAlreadyExistsException {
        Profile profile = this.findByIdAndUserId(UUIDUtils.stringToUUID(data.id()), authenticatedUser.getId());

        Optional<Profile> optionalProfile = this.profileRepository.findByNameAndUserId(data.name(), authenticatedUser.getId());

        if (optionalProfile.isPresent()) {
            throw new ProfileNameAlreadyExistsException();
        }

        profile.setName(data.name());
        Profile updatedProfile = this.profileRepository.save(profile);
        return new UpdateProfileResponseDTO(updatedProfile);
    }
    
    /**
     * Exclui um perfil do sistema.
     * @param authenticatedUser Usuário autenticado.
     * @param id Identificador UUID do perfil a ser excluído.
    */
    @Transactional
    public void delete(User authenticatedUser, UUID id) {
        Profile profile = this.findByIdAndUserId(id, authenticatedUser.getId());
        this.profileRepository.deleteById(profile.getId());
        return;
    }

}
