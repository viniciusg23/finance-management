package com.finapp.financemanagement.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finapp.financemanagement.domain.model.SharingInvite;

@Repository
public interface ISharingInviteRepository extends JpaRepository<SharingInvite, UUID>{

    @Query("SELECT s FROM SharingInvite s WHERE s.receiver.id = :userId")
    List<SharingInvite> findAllByReceiverId(@Param("userId") UUID userId);

    @Query("SELECT s FROM SharingInvite s WHERE s.sender.id = :userId")
    List<SharingInvite> findAllBySenderId(@Param("userId") UUID userId);
    
}
