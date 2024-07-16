package com.finapp.financemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
import com.finapp.financemanagement.domain.service.IconService;
import com.finapp.financemanagement.infrastructure.utils.UUIDUtils;

public class IconServiceTest {

    @InjectMocks
    private IconService iconService;

    @Mock
    private IIconRepository iconRepository;

    private Icon icon;
    private UUID iconId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        icon = Icon.builder()
                .id(UUID.randomUUID())
                .name("Ícone de Teste")
                .thirdPartyId("thirdPartyId")
                .createdAt(LocalDateTime.now())
                .build();
        iconId = icon.getId();
    }

    @Test
    public void testFindById_ShouldReturnIconWhenFound() {
        when(iconRepository.findById(iconId)).thenReturn(Optional.of(icon));

        Icon result = iconService.findById(iconId);

        assertEquals(icon, result);
    }

    @Test
    public void testFindById_ShouldThrowIconNotFoundExceptionWhenNotFound() {
        when(iconRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(IconNotFoundException.class, () -> iconService.findById(iconId));
    }

    @Test
    public void testGet_ShouldReturnGetIconResponseDTOWhenFound() {
        when(iconRepository.findById(iconId)).thenReturn(Optional.of(icon));

        GetIconResponseDTO result = iconService.get(iconId);

        assertEquals(new GetIconResponseDTO(icon), result);
    }

    @Test
    public void testGetAll_ShouldReturnGetAllIconsResponseDTO() {
        List<Icon> icons = List.of(icon);
        when(iconRepository.findAll()).thenReturn(icons);

        GetAllIconsResponseDTO result = iconService.getAll();

        assertEquals(new GetAllIconsResponseDTO(icons.stream().map(GetIconResponseDTO::new).toList()), result);
    }

    @Test
    public void testSave_ShouldSaveIconAndReturnSaveIconResponseDTO() {
        SaveIconRequestDTO saveIconRequestDTO = new SaveIconRequestDTO("Novo Ícone", "newThirdPartyId");

        when(iconRepository.findByThirdPartyId(saveIconRequestDTO.thirdPartyId())).thenReturn(Optional.empty());
        when(iconRepository.save(any(Icon.class))).thenReturn(icon);

        SaveIconResponseDTO result = iconService.save(saveIconRequestDTO);

        assertEquals(new SaveIconResponseDTO(icon), result);
    }

    @Test
    public void testSave_ShouldThrowThirdPartyIdAlreadyExistsExceptionWhenThirdPartyIdAlreadyExists() {
        SaveIconRequestDTO saveIconRequestDTO = new SaveIconRequestDTO("Novo Ícone", "thirdPartyId");

        when(iconRepository.findByThirdPartyId(saveIconRequestDTO.thirdPartyId())).thenReturn(Optional.of(icon));

        assertThrows(ThirdPartyIdAlreadyExistsException.class,
                () -> iconService.save(saveIconRequestDTO));
    }

    @Test
    public void testUpdate_ShouldUpdateIconAndReturnUpdateIconResponseDTO() {
        UpdateIconRequestDTO updateIconRequestDTO = new UpdateIconRequestDTO(
                icon.getId().toString(), "Ícone Atualizado", "updatedThirdPartyId");

        when(iconRepository.findById(UUIDUtils.stringToUUID(updateIconRequestDTO.id()))).thenReturn(Optional.of(icon));
        when(iconRepository.findByThirdPartyId(updateIconRequestDTO.thirdPartyId())).thenReturn(Optional.empty());
        when(iconRepository.save(any(Icon.class))).thenReturn(icon);

        UpdateIconResponseDTO result = iconService.update(updateIconRequestDTO);

        assertEquals(new UpdateIconResponseDTO(icon), result);
    }

    @Test
    public void testUpdate_ShouldThrowThirdPartyIdAlreadyExistsExceptionWhenThirdPartyIdAlreadyExists() {
        UpdateIconRequestDTO updateIconRequestDTO = new UpdateIconRequestDTO(
                icon.getId().toString(), "Ícone Atualizado", "thirdPartyId");

        when(iconRepository.findById(UUIDUtils.stringToUUID(updateIconRequestDTO.id()))).thenReturn(Optional.of(icon));
        when(iconRepository.findByThirdPartyId(updateIconRequestDTO.thirdPartyId())).thenReturn(Optional.of(icon));

        assertThrows(ThirdPartyIdAlreadyExistsException.class,
                () -> iconService.update(updateIconRequestDTO));
    }

    @Test
    public void testDelete_ShouldDeleteIconWhenFound() {
        when(iconRepository.findById(iconId)).thenReturn(Optional.of(icon));

        iconService.delete(iconId);
    }

    @Test
    public void testDelete_ShouldThrowIconNotFoundExceptionWhenNotFound() {
        when(iconRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(IconNotFoundException.class, () -> iconService.delete(iconId));
    }
}