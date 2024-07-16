package com.finapp.financemanagement.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.finapp.financemanagement.domain.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {

    UserDetails findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);

    Optional<User> findByNickname(String nickname);

}
