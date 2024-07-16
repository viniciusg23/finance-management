package com.finapp.financemanagement.application.security;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.finapp.financemanagement.domain.model.User;
import com.finapp.financemanagement.domain.repository.IUserRepository;
import com.finapp.financemanagement.domain.service.AuthorizationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom filter responsible for handling security-related tasks on incoming requests.
 * This filter decodes JWT tokens from the Authorization header, validates them,
 * retrieves user details, and sets up authentication in the Spring Security context.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private  AuthorizationService authorizationService;

    @Autowired
    private IUserRepository userRepository;

    /**
     * Filters incoming HTTP requests, handling security-related tasks.
     *
     * @param request       the HTTP request
     * @param response      the HTTP response
     * @param filterChain   the filter chain to continue processing the request
     * @throws ServletException     if any Servlet-related error occurs
     * @throws IOException          if an I/O error occurs during filtering
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String decodedToken = this.decodeToken(request);

            if (decodedToken != null) {
                String subject = this.authorizationService.validateToken(decodedToken);
                Optional<User> optionalUser = this.userRepository.findById(UUID.fromString(subject));

                UserDetails user = this.userRepository.findByEmail(optionalUser.get().getEmail());
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                request.setAttribute("user", subject);
            }
        } catch (Exception exception) {
            // TODO: Create a Log System
            System.out.println("ERROR: " + exception.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        
        }
    }

    /**
     * Decodes the JWT token from the Authorization header.
     *
     * @param request   the HTTP request
     * @return the decoded JWT token (without the 'Bearer ' prefix), or null if not found
     */
    private String decodeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty()) {
            return null;
        }

        return authorization.replace("Bearer ", "");
    }

}
