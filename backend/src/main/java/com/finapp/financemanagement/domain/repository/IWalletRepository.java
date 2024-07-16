package com.finapp.financemanagement.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finapp.financemanagement.domain.model.Wallet;

@Repository
public interface IWalletRepository extends JpaRepository<Wallet, UUID> {
    
    Optional<Wallet> findByName(String name);

    @Query("SELECT w FROM Wallet w WHERE w.profile.id = :profileId")
    List<Wallet> findByProfileId(@Param("profileId") UUID profileId);

    @Query("SELECT w FROM Wallet w WHERE w.name = :name AND w.profile.id = :profileId")
    Optional<Wallet> findByNameAndProfileId(@Param("name") String name, @Param("profileId") UUID profileId);

    @Query("SELECT w FROM Wallet w WHERE w.id = :id AND w.user.id = :userId")
    Optional<Wallet> findByIdAndUserId(@Param("id") UUID id, @Param("userId") UUID userId);

}
