package com.finapp.financemanagement.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Configuration class for web-related configurations such as CORS (Cross-Origin Resource Sharing).
 */
@Configuration
public class WebConfiguration {

    /**
     * Defines a bean for configuring CORS (Cross-Origin Resource Sharing) for the application.
     *
     * @return CorsConfigurationSource - A source for CORS configuration.
     */
    @Bean
    public CorsConfigurationSource corsConfigurerSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST","PUT", "DELETE", "OPTIONS", "HEAD"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
        
    }
}