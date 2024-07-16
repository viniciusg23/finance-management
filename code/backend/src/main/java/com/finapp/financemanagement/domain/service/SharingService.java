package com.finapp.financemanagement.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finapp.financemanagement.domain.exception.profile.ProfileNotFoundException;
import com.finapp.financemanagement.domain.exception.sharing.SharingNotFoundException;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.Sharing;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.ISharingRepository;

import jakarta.transaction.Transactional;

/**
 * Serviço para gerenciamento de compartilhamentos.
*/
@Service
public class SharingService {
    
    @Autowired
    private ISharingRepository sharingRepository;

    /**
     * Busca um compartilhamento pelo seu identificador.
     * @param sharingId Identificador UUID do compartilhamento.
     * @return O compartilhamento encontrado.
     * @throws SharingNotFoundException se o compartilhamento não for encontrado.
     */
    public Sharing findById(UUID sharingId) {
        Optional<Sharing> optionalSharing = this.sharingRepository.findById(sharingId);

        if(optionalSharing.isEmpty()){
            throw new SharingNotFoundException();
        }

        return optionalSharing.get();
    }

    /**
     * Busca um perfil pelo identificador do perfil e pelo identificador do usuário.
     * @param id Identificador UUID do perfil.
     * @param userId Identificador UUID do usuário associado.
     * @return O perfil encontrado.
     * @throws ProfileNotFoundException se o perfil não for encontrado.
    */
    public Profile findByProfileIdAndGuestId(UUID id, UUID userId) throws ProfileNotFoundException {
        Optional<Sharing> optionalSharing = this.sharingRepository.findByProfileIdAndGuestId(id, userId);

        if(optionalSharing.isEmpty()){
            throw new ProfileNotFoundException();
        }

        return optionalSharing.get().getProfile();
    }

    /**
     * Salva um novo compartilhamento no banco de dados.
     * @param sharing O compartilhamento a ser salvo.
     * @return O compartilhamento salvo.
    */
    @Transactional
    public Sharing save(Sharing sharing){
        return this.sharingRepository.save(sharing);
    }

    /**
     * Deleta um compartilhamento do banco de dados.
     * @param authenticatedUser Usuário autenticado.
     * @param sharingId Identificador UUID do compartilhamento.
     * @throws SharingNotFoundException se o compartilhamento não for encontrado.
    */
    public void delete(User authenticatedUser, UUID sharingId) {
        Sharing sharing = this.findById(sharingId);

        if(!sharing.getOwner().getId().equals(authenticatedUser.getId()) && sharing.getGuest().getId().equals(authenticatedUser.getId())){
            throw new SharingNotFoundException();
        }

        this.sharingRepository.delete(sharing);
    }

    /**
     * Busca todos os perfis compartilhados com um usuário.
     * @param userId Identificador UUID do usuário.
     * @return Lista de perfis compartilhados.
    */
    public List<Profile> findAllProfilesByGuestId(UUID userId) {
        List<Sharing> shares = this.sharingRepository.findAllProfilesByGuestId(userId);
        return shares.stream().map(Sharing::getProfile).toList();
    }
    
    /**
     * Busca um perfil pelo nome e pelo identificador do usuário.
     * @param name Nome do perfil.
     * @param userId Identificador UUID do usuário associado.
     * @return O perfil encontrado.
     * @throws ProfileNotFoundException se o perfil não for encontrado.
    */
    public Profile findByProfileNameAndGuestId(String name, UUID userId) throws ProfileNotFoundException {
        Optional<Sharing> optionalSharing = this.sharingRepository.findByProfileNameAndGuestId(name, userId);

        if (optionalSharing.isEmpty()) {
            throw new ProfileNotFoundException();
        }

        return optionalSharing.get().getProfile();
    }

   
}
