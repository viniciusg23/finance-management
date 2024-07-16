package com.finapp.financemanagement.domain.service;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finapp.financemanagement.domain.dto.category.GetCategoryResponseDTO;
import com.finapp.financemanagement.domain.dto.category.SaveCategoryRequestDTO;
import com.finapp.financemanagement.domain.dto.category.SaveCategoryResponseDTO;
import com.finapp.financemanagement.domain.dto.category.UpdateCategoryRequestDTO;
import com.finapp.financemanagement.domain.dto.category.UpdateCategoryResponseDTO;
import com.finapp.financemanagement.domain.exception.category.CategoryNameAlreadyExistsException;
import com.finapp.financemanagement.domain.exception.category.CategoryNotFoundException;
import com.finapp.financemanagement.domain.model.Category;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.ICategoryRepository;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 * Serviço para gerenciamento de categorias.
 */
@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private IconService iconService;

    /**
     * Busca uma categoria pelo seu identificador.
     *
     * @param id Identificador UUID da categoria.
     * @return A categoria encontrada.
     * @throws CategoryNotFoundException se a categoria não for encontrada.
     */
    public Category findById(UUID id){
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        return optionalCategory.orElseThrow(CategoryNotFoundException::new);
    }

    /**
     * Busca uma categoria pelo identificador da categoria e pelo identificador do perfil.
     *
     * @param id Identificador UUID da categoria.
     * @param profileId Identificador UUID do perfil associado.
     * @return A categoria encontrada.
     * @throws CategoryNotFoundException se a categoria não for encontrada.
     */
    public Category findByIdAndProfileId(UUID id, UUID profileId) {
        Optional<Category> optionalCategory = this.categoryRepository.findByIdAndProfileId(id, profileId);
        return optionalCategory.orElseThrow(CategoryNotFoundException::new);
    }

    /**
     * Retorna os detalhes de uma categoria para um usuário autenticado.
     *
     * @param authenticatedUser Usuário autenticado.
     * @param id Identificador UUID da categoria.
     * @return DTO com os detalhes da categoria.
     * @throws CategoryNotFoundException se a categoria não for encontrada.
     */
    public GetCategoryResponseDTO get(User authenticatedUser, UUID id) {
        Category category = this.findById(id);
        this.profileService.findByIdAndUserId(category.getProfile().getId(), authenticatedUser.getId());
        return new GetCategoryResponseDTO(category);
    }

    /**
     * Salva uma nova categoria no sistema.
     *
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com informações para salvar a categoria.
     * @return DTO com os detalhes da categoria salva.
     * @throws CategoryNameAlreadyExistsException se o nome da categoria já existir.
     */
    @Transactional
    public SaveCategoryResponseDTO save(User authenticatedUser, @Valid SaveCategoryRequestDTO data) {
        Profile profile = this.profileService.findByIdAndUserId(UUIDUtils.stringToUUID(data.profileId()), authenticatedUser.getId());
        Optional<Category> optionalCategory = this.categoryRepository.findByNameAndProfileId(data.name(), profile.getId());
        optionalCategory.ifPresent(s -> { throw new CategoryNameAlreadyExistsException(); });

        Category category = Category.builder()
            .name(data.name())
            .icon(this.iconService.findById(UUIDUtils.stringToUUID(data.iconId())))
            .type(data.type())
            .profile(profile)
            .build();

        Category savedCategory = this.categoryRepository.save(category);
        return new SaveCategoryResponseDTO(savedCategory);
    }

    /**
     * Atualiza uma categoria existente no sistema.
     *
     * @param authenticatedUser Usuário autenticado.
     * @param data DTO com informações para atualizar a categoria.
     * @return DTO com os detalhes da categoria atualizada.
     * @throws CategoryNameAlreadyExistsException se o nome da categoria já existir para outro registro.
     */
    @Transactional
    public UpdateCategoryResponseDTO update(User authenticatedUser, @Valid UpdateCategoryRequestDTO data) {
        Category category = this.findById(UUIDUtils.stringToUUID(data.id()));
        Profile profile = this.profileService.findByIdAndUserId(category.getProfile().getId(), authenticatedUser.getId());

        Optional<Category> optionalCategory = this.categoryRepository.findByNameAndProfileId(data.name(), profile.getId());
        optionalCategory.ifPresent(s -> { throw new CategoryNameAlreadyExistsException(); });

        category.setName(data.name());
        category.setIcon(this.iconService.findById(UUIDUtils.stringToUUID(data.iconId())));
        category.setType(data.type());

        Category savedCategory = this.categoryRepository.save(category);
        return new UpdateCategoryResponseDTO(savedCategory);
    }

    /**
     * Exclui uma categoria do sistema.
     *
     * @param authenticatedUser Usuário autenticado.
     * @param id Identificador UUID da categoria a ser excluída.
     * @throws CategoryNotFoundException se a categoria não for encontrada.
     */
    @Transactional
    public void delete(User authenticatedUser, UUID id) {
        Category category = this.findById(id);
        this.profileService.findByIdAndUserId(category.getProfile().getId(), authenticatedUser.getId());
        this.categoryRepository.delete(category);
    }
}
