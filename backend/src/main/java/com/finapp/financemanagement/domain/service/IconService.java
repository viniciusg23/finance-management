package com.finapp.financemanagement.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finapp.financemanagement.domain.dto.icon.GetAllIconsResponseDTO;
import com.finapp.financemanagement.domain.dto.icon.GetIconResponseDTO;
import com.finapp.financemanagement.domain.dto.icon.SaveIconRequestDTO;
import com.finapp.financemanagement.domain.dto.icon.SaveIconResponseDTO;
import com.finapp.financemanagement.domain.dto.icon.UpdateIconRequestDTO;
import com.finapp.financemanagement.domain.dto.icon.UpdateIconResponseDTO;
import com.finapp.financemanagement.domain.exception.icon.IconNotFoundException;
import com.finapp.financemanagement.domain.exception.icon.ThirdPartyIdAlreadyExistsException;
import com.finapp.financemanagement.domain.model.Icon;
import com.finapp.financemanagement.domain.repository.IIconRepository;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;

import jakarta.transaction.Transactional;
/**
 * Serviço para gerenciamento de ícones.
 */
@Service
public class IconService {

    @Autowired
    private IIconRepository iconRepository;

    /**
     * Busca um ícone pelo seu identificador.
     *
     * @param id Identificador UUID do ícone.
     * @return O ícone encontrado.
     * @throws IconNotFoundException se o ícone não for encontrado.
     */
    public Icon findById(UUID id) throws IconNotFoundException {
        Optional<Icon> icon = this.iconRepository.findById(id);
        return icon.orElseThrow(IconNotFoundException::new);
    }

    /**
     * Obtém um ícone e retorna seu DTO de resposta.
     *
     * @param id Identificador UUID do ícone.
     * @return DTO de resposta contendo os detalhes do ícone.
     */
    public GetIconResponseDTO get(UUID id) {
        Icon icon = this.findById(id);
        return new GetIconResponseDTO(icon);
    }

    public GetAllIconsResponseDTO getAll() {
        List<Icon> icons = this.iconRepository.findAll();
        return new GetAllIconsResponseDTO(icons.stream().map(GetIconResponseDTO::new).toList());
    }

    /**
     * Salva um novo ícone no banco de dados.
     *
     * @param data DTO com informações para criar um novo ícone.
     * @return DTO de resposta com os detalhes do ícone salvo.
     * @throws ThirdPartyIdAlreadyExistsException se o identificador de terceiros já existir.
     */
    @Transactional
    public SaveIconResponseDTO save(SaveIconRequestDTO data) throws ThirdPartyIdAlreadyExistsException {
        if (this.iconRepository.findByThirdPartyId(data.thirdPartyId()).isPresent()) {
            throw new ThirdPartyIdAlreadyExistsException();
        }

        Icon icon = Icon.builder()
                .name(data.name())
                .thirdPartyId(data.thirdPartyId())
                .build();

        Icon savedIcon = this.iconRepository.save(icon);
        return new SaveIconResponseDTO(savedIcon);
    }

    /**
     * Atualiza um ícone existente.
     *
     * @param data DTO com informações para atualizar o ícone.
     * @return DTO de resposta com os detalhes do ícone atualizado.
     * @throws ThirdPartyIdAlreadyExistsException se o identificador de terceiros já existir para outro ícone.
     */
    @Transactional
    public UpdateIconResponseDTO update(UpdateIconRequestDTO data) throws ThirdPartyIdAlreadyExistsException {
        Icon icon = this.findById(UUIDUtils.stringToUUID(data.id()));

        if (this.iconRepository.findByThirdPartyId(data.thirdPartyId()).isPresent()) {
            throw new ThirdPartyIdAlreadyExistsException();
        }

        icon.setName(data.name());
        icon.setThirdPartyId(data.thirdPartyId());

        Icon updatedIcon = this.iconRepository.save(icon);
        return new UpdateIconResponseDTO(updatedIcon);
    }

    /**
     * Exclui um ícone do sistema.
     *
     * @param id Identificador UUID do ícone a ser excluído.
     */
    @Transactional
    public void delete(UUID id) {
        Icon icon = this.findById(id);
        this.iconRepository.deleteById(icon.getId());
    }

   
}