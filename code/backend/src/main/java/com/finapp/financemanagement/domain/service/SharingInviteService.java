package com.finapp.financemanagement.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.finapp.financemanagement.domain.model.Sharing;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.ISharingInviteRepository;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;

import jakarta.transaction.Transactional;

/**
 * Serviço para gerenciamento de convites de compartilhamento.
 */
@Service
public class SharingInviteService {

    @Autowired
    private ISharingInviteRepository sharingInviteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SharingService sharingService;

    /**
     * Busca um convite de compartilhamento pelo seu identificador.
     *
     * @param sharingInviteId Identificador UUID do convite de compartilhamento.
     * @return O convite de compartilhamento encontrado.
     * @throws InviteNotFoundException se o convite de compartilhamento não for encontrado.
    */
    public SharingInvite findById(UUID sharingInviteId) throws InviteNotFoundException {
        Optional<SharingInvite> optionalSharingInvite = this.sharingInviteRepository.findById(sharingInviteId);

        if (optionalSharingInvite.isEmpty()) {
            throw new InviteNotFoundException();
        }

        return optionalSharingInvite.get();
    }
    /**
     * Busca todos os convites de compartilhamento recebidos por um usuário.
     * @param authenticatedUser
     * @return DTO de resposta contendo os detalhes dos convites de compartilhamento recebidos.
     * @throws InviteNotFoundException se o convite de compartilhamento não for encontrado.
     */
    public GetAllUserInvitesResponseDTO findAllReceivedByUserId(User authenticatedUser) {
        List<SharingInvite> sharingInvites = this.sharingInviteRepository
                .findAllByReceiverId(authenticatedUser.getId());
        List<GetUserInviteResponseDTO> userReceivedInvites = sharingInvites.stream()
                .filter(sharingInvite -> sharingInvite.getStatus().equals(SharingInviteStatus.PENDING))
                .map(sharingInvite -> new GetUserInviteResponseDTO(sharingInvite))
                .toList();

        return new GetAllUserInvitesResponseDTO(userReceivedInvites);
    }

    /**
     * Busca todos os convites de compartilhamento enviados por um usuário.
     * @param authenticatedUser
     * @return DTO de resposta contendo os detalhes dos convites de compartilhamento enviados.
     * @throws InviteNotFoundException se o convite de compartilhamento não for encontrado.
     */
    public GetAllUserInvitesResponseDTO findAllSentByUserId(User authenticatedUser) {
        List<SharingInvite> sharingInvites = this.sharingInviteRepository.findAllBySenderId(authenticatedUser.getId());
        List<GetUserInviteResponseDTO> userReceivedInvites = sharingInvites.stream()
                .filter(sharingInvite -> sharingInvite.getStatus().equals(SharingInviteStatus.PENDING))
                .map(sharingInvite -> new GetUserInviteResponseDTO(sharingInvite))
                .toList();

        return new GetAllUserInvitesResponseDTO(userReceivedInvites);
    }

    /**
     * Salva um novo convite de compartilhamento no banco de dados.
     *
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com informações para criar um novo convite de compartilhamento.
     * @return DTO de resposta com os detalhes do convite de compartilhamento salvo.
    */
    @Transactional
    public SaveSharingInviteResponseDTO save(User authenticatedUser, SaveSharingInviteRequestDTO data) {
        User sender = this.userService.findById(authenticatedUser.getId());
        User receiver = this.userService.findByNickname(data.receiverNickname());
        Profile profile = this.profileService.findByIdAndUserId(UUIDUtils.stringToUUID(data.profileId()), sender.getId());

        SharingInvite sharingInvite = SharingInvite.builder()
                .sender(sender)
                .receiver(receiver)
                .profile(profile)
                .status(SharingInviteStatus.PENDING)
                .build();

        SharingInvite savedSharingInvite = this.sharingInviteRepository.save(sharingInvite);
        return new SaveSharingInviteResponseDTO(savedSharingInvite);
    }

    /**
     * Aceita um convite de compartilhamento.
     *
     * @param authenticatedUser Usuário autenticado.
     * @param sharingInviteId Identificador UUID do convite de compartilhamento.
     * @throws InviteNotFoundException se o convite de compartilhamento não for encontrado.
     * @throws InviteAlreadyAcceptedException se o convite de compartilhamento já foi aceito.
     * @throws InviteAlreadyRefusedException se o convite de compartilhamento já foi recusado.
    */
    public void accept(User authenticatedUser, UUID sharingInviteId) {
        SharingInvite sharingInvite = this.findById(sharingInviteId);

        if (!sharingInvite.getReceiver().getId().equals(authenticatedUser.getId())) {
            throw new InviteNotFoundException();
        }

        if (sharingInvite.getStatus().equals(SharingInviteStatus.ACCEPTED)) {
            throw new InviteAlreadyAcceptedException();
        }

        if (sharingInvite.getStatus().equals(SharingInviteStatus.REFUSED)) {
            throw new InviteAlreadyRefusedException();
        }

        sharingInvite.setStatus(SharingInviteStatus.ACCEPTED);
        this.sharingInviteRepository.save(sharingInvite);

        Sharing sharing = Sharing.builder()
                .owner(sharingInvite.getSender())
                .guest(authenticatedUser)
                .profile(sharingInvite.getProfile())
                .build();

        this.sharingService.save(sharing);
    }

    /**
     * Recusa um convite de compartilhamento.
     *
     * @param authenticatedUser Usuário autenticado.
     * @param sharingInviteId Identificador UUID do convite de compartilhamento.
     * @throws InviteNotFoundException se o convite de compartilhamento não for encontrado.
     * @throws InviteAlreadyAcceptedException se o convite de compartilhamento já foi aceito.
     * @throws InviteAlreadyRefusedException se o convite de compartilhamento já foi recusado.
    */
    public void refuse(User authenticatedUser, UUID sharingInviteId) {
        SharingInvite sharingInvite = this.findById(sharingInviteId);

        if (!sharingInvite.getReceiver().getId().equals(authenticatedUser.getId())
                && !sharingInvite.getSender().getId().equals(authenticatedUser.getId())) {
            throw new InviteNotFoundException();
        }

        if (sharingInvite.getStatus().equals(SharingInviteStatus.ACCEPTED)) {
            throw new InviteAlreadyAcceptedException();
        }

        if (sharingInvite.getStatus().equals(SharingInviteStatus.REFUSED)) {
            throw new InviteAlreadyRefusedException();
        }

        sharingInvite.setStatus(SharingInviteStatus.REFUSED);
        this.sharingInviteRepository.save(sharingInvite);
    }
}
