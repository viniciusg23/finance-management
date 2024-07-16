package com.finapp.financemanagement.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.financemanagement.domain.dto.icon.GetAllIconsResponseDTO;
import com.finapp.financemanagement.domain.dto.icon.GetIconResponseDTO;
import com.finapp.financemanagement.domain.dto.icon.SaveIconRequestDTO;
import com.finapp.financemanagement.domain.dto.icon.SaveIconResponseDTO;
import com.finapp.financemanagement.domain.dto.icon.UpdateIconRequestDTO;
import com.finapp.financemanagement.domain.dto.icon.UpdateIconResponseDTO;
import com.finapp.financemanagement.domain.service.IconService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Controller class for managing operations related to icons.
 */
@RestController
@RequestMapping("/icon")
@Validated
public class IconController {

    @Autowired
    private IconService iconService;

    @GetMapping("")
    public ResponseEntity<GetAllIconsResponseDTO> getAllIcons(){
        GetAllIconsResponseDTO response = this.iconService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves an icon by its ID.
     *
     * @param id The ID of the icon to retrieve.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetIconResponseDTO> getIcon(@PathVariable @NotNull UUID id) {
        GetIconResponseDTO response = this.iconService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Saves a new icon.
     *
     * @param data The data of the icon to save.
     * @return ResponseEntity containing the response data.
     */
    @PostMapping("")
    public ResponseEntity<SaveIconResponseDTO> saveIcon(@RequestBody @Valid SaveIconRequestDTO data) {
        SaveIconResponseDTO response = this.iconService.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Updates an icon.
     *
     * @param data The data of the icon to update.
     * @return ResponseEntity containing the response data.
     */
    @PutMapping("")
    public ResponseEntity<UpdateIconResponseDTO> updateIcon(@RequestBody @Valid UpdateIconRequestDTO data) {
        UpdateIconResponseDTO response = this.iconService.update(data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Deletes an icon.
     *
     * @param id The ID of the icon to delete.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIcon(@PathVariable @NotNull UUID id) {
        this.iconService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
