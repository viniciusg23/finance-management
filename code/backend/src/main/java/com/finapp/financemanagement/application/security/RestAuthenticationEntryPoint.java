package com.finapp.financemanagement.application.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finapp.financemanagement.domain.error.UserError;
import com.finapp.financemanagement.domain.exception.FinanceManagementApplicationException;
import com.finapp.financemanagement.domain.exception.RestExceptionResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom implementation of Spring Security's AuthenticationEntryPoint interface to handle authentication failures in a RESTful API context.
 * This class is responsible for sending an appropriate HTTP response when authentication fails for incoming requests.
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    /**
     * Invoked when authentication fails. This method sets the HTTP status code to 401 (UNAUTHORIZED) and sends a JSON response containing details about the authentication failure.
     *
     * @param request        the HTTP request
     * @param response       the HTTP response
     * @param authException  the authentication exception that occurred
     * @throws IOException   if an I/O error occurs during writing the response
     * @throws ServletException  if any Servlet related error occurs
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        RestExceptionResponse data = new RestExceptionResponse(HttpStatus.UNAUTHORIZED.value(), 
                FinanceManagementApplicationException.getCurrentTimestamp(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), UserError.INVALID_AUTHENTICATION.getError().getName(), UserError.INVALID_AUTHENTICATION.getError().getDescription());

        byte[] body = new ObjectMapper().writeValueAsBytes(data);
        response.getOutputStream().write(body);
    }

}
