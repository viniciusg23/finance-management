package com.finapp.financemanagement.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a user of the financial management system.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
public class User implements UserDetails {

    /**
     * The unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The full name of the user.
     */
    @NotBlank
    @Column(nullable = false)
    private String name;

    /**
     * The nickname of the user.
     */
    @NotBlank
    @Column(nullable = false, unique = true)
    private String nickname;

    /**
     * The email address of the user.
     */
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The password of the user.
     */
    @JsonIgnore
    @NotBlank
    @Column(nullable = false)
    private String password;

    /**
     * The profiles associated with the user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Profile> profiles;

    /**
     * The date and time when the user was created.
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically sets the creation date and time before persisting the user.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    /**
     * Retrieves the authorities granted to the user.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    /**
     * Retrieves the username used to authenticate the user.
     *
     * @return the username (email) of the user
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true if the user's account is valid, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * @return true if the user is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     *
     * @return true if the user's credentials are valid, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
