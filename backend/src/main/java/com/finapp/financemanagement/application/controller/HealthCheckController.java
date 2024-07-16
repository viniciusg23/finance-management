package com.finapp.financemanagement.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing operations related to application health.
 */
@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {
    
    @GetMapping("")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
