package com.finapp.financemanagement.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finapp.financemanagement.domain.model.Category;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID>{

    @Query("SELECT c FROM Category c WHERE c.id = :id AND c.profile.id = :profileId")
    Optional<Category> findByIdAndProfileId(@Param("id") UUID id, @Param("profileId") UUID profileId);

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.profile.id = :id")
    Optional<Category> findByNameAndProfileId(@Param("name") String name, @Param("id") UUID id);
     
}
