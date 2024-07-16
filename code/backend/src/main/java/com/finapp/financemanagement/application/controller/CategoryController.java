package com.finapp.financemanagement.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.financemanagement.domain.dto.category.GetCategoryResponseDTO;
import com.finapp.financemanagement.domain.dto.category.SaveCategoryRequestDTO;
import com.finapp.financemanagement.domain.dto.category.SaveCategoryResponseDTO;
import com.finapp.financemanagement.domain.dto.category.UpdateCategoryRequestDTO;
import com.finapp.financemanagement.domain.dto.category.UpdateCategoryResponseDTO;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Controller class for managing operations related to categories.
 */
@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    /**
     * Retrieves a category by its ID.
     *
     * @param authenticatedUser The authenticated user.
     * @param id                The ID of the category to retrieve.
     * @return ResponseEntity containing the response data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryResponseDTO> getCategory(Authentication authenticatedUser, @PathVariable @NotNull UUID id) {
        GetCategoryResponseDTO response = this.categoryService.get((User) authenticatedUser.getPrincipal(), id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Saves a new category.
     *
     * @param authenticatedUser The authenticated user.
     * @param data              The data of the category to save.
     * @return ResponseEntity containing the response data.
     */
    @PostMapping("")
    public ResponseEntity<SaveCategoryResponseDTO> saveCategory(Authentication authenticatedUser, @RequestBody @Valid SaveCategoryRequestDTO data) {
        SaveCategoryResponseDTO response = this.categoryService.save((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Updates an existing category.
     *
     * @param authenticatedUser The authenticated user.
     * @param data              The updated data of the category.
     * @return ResponseEntity containing the response data.
     */
    @PutMapping("")
    public ResponseEntity<UpdateCategoryResponseDTO> updateCategory(Authentication authenticatedUser, @RequestBody @Valid UpdateCategoryRequestDTO data) {
        UpdateCategoryResponseDTO response = this.categoryService.update((User) authenticatedUser.getPrincipal(), data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Deletes a category by its ID.
     *
     * @param authenticatedUser The authenticated user.
     * @param id                The ID of the category to delete.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIcon(Authentication authenticatedUser, @PathVariable @NotNull UUID id) {
        this.categoryService.delete((User) authenticatedUser.getPrincipal(), id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
