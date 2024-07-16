package com.finapp.financemanagement.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finapp.financemanagement.domain.model.Profile;

@Repository
public interface IProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByName(String name);

    @Query("SELECT p FROM Profile p WHERE p.user.id = :userId")
    List<Profile> findAllByUserId(@Param("userId") UUID userId);

   @Query("SELECT p FROM Profile p WHERE p.name = :name AND p.user.id = :userId")
    Optional<Profile> findByNameAndUserId(@Param("name") String name, @Param("userId") UUID userId);

    
    @Query("SELECT p FROM Profile p WHERE p.id = :id AND p.user.id = :userId")
    Optional<Profile> findByIdAndUserId(@Param("id") UUID id, @Param("userId") UUID userId);

}
