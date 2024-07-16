package com.finapp.financemanagement.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finapp.financemanagement.domain.model.Sharing;

public interface ISharingRepository extends JpaRepository<Sharing, UUID> {

    @Query("SELECT s FROM Sharing s WHERE s.guest.id = :userId")
    List<Sharing> findAllProfilesByGuestId(@Param("userId") UUID userId);

    @Query("SELECT s FROM Sharing s WHERE s.profile.id = :id AND s.guest.id = :userId")
    Optional<Sharing> findByProfileIdAndGuestId(@Param("id") UUID id, @Param("userId") UUID userId);

    @Query("SELECT s FROM Sharing s WHERE s.profile.name = :name AND s.guest.id = :userId")
    Optional<Sharing> findByProfileNameAndGuestId(@Param("name") String name, @Param("userId") UUID userId);
    
}
