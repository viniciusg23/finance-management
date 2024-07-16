package com.finapp.financemanagement.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finapp.financemanagement.domain.model.Icon;

@Repository
public interface IIconRepository extends JpaRepository<Icon, UUID> {

    Optional<Icon> findByThirdPartyId(String thirdPartyId);

}
