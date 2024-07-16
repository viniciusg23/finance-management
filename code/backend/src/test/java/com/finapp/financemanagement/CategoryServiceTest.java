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

import com.finapp.financemanagement.domain.dto.category.GetCategoryResponseDTO;
import com.finapp.financemanagement.domain.dto.category.SaveCategoryRequestDTO;
import com.finapp.financemanagement.domain.dto.category.SaveCategoryResponseDTO;
import com.finapp.financemanagement.domain.dto.category.UpdateCategoryRequestDTO;
import com.finapp.financemanagement.domain.dto.category.UpdateCategoryResponseDTO;
import com.finapp.financemanagement.domain.enums.TransactionType;
import com.finapp.financemanagement.domain.exception.category.CategoryNameAlreadyExistsException;
import com.finapp.financemanagement.domain.exception.category.CategoryNotFoundException;
import com.finapp.financemanagement.domain.model.Category;
import com.finapp.financemanagement.domain.model.Icon;
import com.finapp.financemanagement.domain.model.Profile;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.ICategoryRepository;
import com.finapp.financemanagement.domain.service.CategoryService;
import com.finapp.financemanagement.domain.service.IconService;
import com.finapp.financemanagement.domain.service.ProfileService;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;

public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ProfileService profileService;

    @Mock
    private IconService iconService;

    private User authenticatedUser;
    private Profile profile;
    private Icon icon;
    private Category category;
    private UUID categoryId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        authenticatedUser = User.builder()
                .id(UUID.randomUUID())
                .name("Usuário de Teste")
                .nickname("usuarioTeste")
                .email("usuario@teste.com")
                .createdAt(LocalDateTime.now())
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
        category = Category.builder()
                .id(UUID.randomUUID())
                .name("Categoria de Teste")
                .icon(icon)
                .type(TransactionType.CREDIT)
                .profile(profile)
                .createdAt(LocalDateTime.now())
                .build();
        categoryId = category.getId();
    }

    @Test
    public void testFindById_ShouldReturnCategoryWhenFound() {
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Category result = categoryService.findById(categoryId);

        assertEquals(category, result);
    }

    @Test
    public void testFindById_ShouldThrowCategoryNotFoundExceptionWhenNotFound() {
        when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.findById(categoryId));
    }

    @Test
    public void testFindByIdAndProfileId_ShouldReturnCategoryWhenFound() {
        when(categoryRepository.findByIdAndProfileId(categoryId, profile.getId())).thenReturn(Optional.of(category));

        Category result = categoryService.findByIdAndProfileId(categoryId, profile.getId());

        assertEquals(category, result);
    }

    @Test
    public void testFindByIdAndProfileId_ShouldThrowCategoryNotFoundExceptionWhenNotFound() {
        when(categoryRepository.findByIdAndProfileId(any(UUID.class), any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.findByIdAndProfileId(categoryId, profile.getId()));
    }

    @Test
    public void testGet_ShouldReturnGetCategoryResponseDTOWhenFound() {
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(profileService.findByIdAndUserId(profile.getId(), authenticatedUser.getId())).thenReturn(profile);

        GetCategoryResponseDTO result = categoryService.get(authenticatedUser, categoryId);

        assertEquals(new GetCategoryResponseDTO(category), result);
    }

    @Test
    public void testGet_ShouldThrowCategoryNotFoundExceptionWhenNotFound() {
        when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.get(authenticatedUser, categoryId));
    }

    @Test
    public void testSave_ShouldSaveCategoryAndReturnSaveCategoryResponseDTO() {
        SaveCategoryRequestDTO saveCategoryRequestDTO = new SaveCategoryRequestDTO(
                profile.getId().toString(),
                icon.getId().toString(),
                "Nova Categoria",
                TransactionType.CREDIT);

        when(profileService.findByIdAndUserId(UUIDUtils.stringToUUID(saveCategoryRequestDTO.profileId()),
                authenticatedUser.getId())).thenReturn(profile);
        when(iconService.findById(UUIDUtils.stringToUUID(saveCategoryRequestDTO.iconId()))).thenReturn(icon);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        SaveCategoryResponseDTO result = categoryService.save(authenticatedUser, saveCategoryRequestDTO);

        assertEquals(new SaveCategoryResponseDTO(category), result);
    }

    @Test
    public void testSave_ShouldThrowCategoryNameAlreadyExistsExceptionWhenNameAlreadyExists() {
        SaveCategoryRequestDTO saveCategoryRequestDTO = new SaveCategoryRequestDTO(
                profile.getId().toString(),
                icon.getId().toString(),
                "Categoria de Teste",
                TransactionType.CREDIT);

        when(profileService.findByIdAndUserId(UUIDUtils.stringToUUID(saveCategoryRequestDTO.profileId()),
                authenticatedUser.getId())).thenReturn(profile);
        when(iconService.findById(UUIDUtils.stringToUUID(saveCategoryRequestDTO.iconId()))).thenReturn(icon);
        when(categoryRepository.findByNameAndProfileId(saveCategoryRequestDTO.name(), profile.getId()))
                .thenReturn(Optional.of(category));

        assertThrows(CategoryNameAlreadyExistsException.class,
                () -> categoryService.save(authenticatedUser, saveCategoryRequestDTO));
    }

    @Test
    public void testUpdate_ShouldUpdateCategoryAndReturnUpdateCategoryResponseDTO() {
        UpdateCategoryRequestDTO updateCategoryRequestDTO = new UpdateCategoryRequestDTO(
                category.getId().toString(),
                icon.getId().toString(),
                "Categoria Atualizada",
                TransactionType.DEBIT);

        when(categoryRepository.findById(UUIDUtils.stringToUUID(updateCategoryRequestDTO.id()))).thenReturn(Optional.of(category));
        when(profileService.findByIdAndUserId(profile.getId(), authenticatedUser.getId())).thenReturn(profile);
        when(iconService.findById(UUIDUtils.stringToUUID(updateCategoryRequestDTO.iconId()))).thenReturn(icon);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        UpdateCategoryResponseDTO result = categoryService.update(authenticatedUser, updateCategoryRequestDTO);

        assertEquals(new UpdateCategoryResponseDTO(category), result);
    }

    @Test
    public void testUpdate_ShouldThrowCategoryNameAlreadyExistsExceptionWhenNameAlreadyExists() {
        UpdateCategoryRequestDTO updateCategoryRequestDTO = new UpdateCategoryRequestDTO(
                category.getId().toString(),
                icon.getId().toString(),
                "Nova Categoria",
                TransactionType.CREDIT);

        when(categoryRepository.findById(UUIDUtils.stringToUUID(updateCategoryRequestDTO.id()))).thenReturn(Optional.of(category));
        when(profileService.findByIdAndUserId(profile.getId(), authenticatedUser.getId())).thenReturn(profile);
        when(iconService.findById(UUIDUtils.stringToUUID(updateCategoryRequestDTO.iconId()))).thenReturn(icon);
        when(categoryRepository.findByNameAndProfileId(updateCategoryRequestDTO.name(), profile.getId()))
                .thenReturn(Optional.of(category));

        assertThrows(CategoryNameAlreadyExistsException.class,
                () -> categoryService.update(authenticatedUser, updateCategoryRequestDTO));
    }

    @Test
    public void testDelete_ShouldDeleteCategoryWhenFound() {
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(profileService.findByIdAndUserId(profile.getId(), authenticatedUser.getId())).thenReturn(profile);

        categoryService.delete(authenticatedUser, categoryId);

        // Verificar se o método delete do repositório foi chamado
    }

    @Test
    public void testDelete_ShouldThrowCategoryNotFoundExceptionWhenNotFound() {
        when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.delete(authenticatedUser, categoryId));
    }
}