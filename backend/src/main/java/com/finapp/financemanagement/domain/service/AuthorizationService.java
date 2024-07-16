package com.finapp.financemanagement.domain.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.IUserRepository;

/**
 * Service for user authentication and authorization.
 */
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;
    @Value("${api.security.jwt.secret}")
    private String secret;

    /**
     * Loads user details based on the provided username.
     *
     * @param username the username of the user to be loaded.
     * @return the user details.
     * @throws UsernameNotFoundException if no user is found with the provided
     *                                   username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = this.userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }
        return user;
    }

    /**
     * Generates a JWT token for a specific user.
     *
     * @param user the user for which the token will be generated.
     * @return the generated JWT token.
     * @throws RuntimeException if an error occurs during token creation.
     */
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("finapp-api")
                    .withSubject(user.getId().toString())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * Validates a JWT token and returns the subject of the token if valid.
     *
     * @param token the JWT token to be validated.
     * @return the subject of the token if valid, or an empty string if the token is
     *         invalid.
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("finapp-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    /**
     * Generates the expiration date for the JWT token.
     *
     * @return the expiration date as an Instant.
     */
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
    }
}
