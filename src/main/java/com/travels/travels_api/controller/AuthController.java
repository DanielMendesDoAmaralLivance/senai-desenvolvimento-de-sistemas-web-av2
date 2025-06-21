package com.travels.travels_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travels.travels_api.dto.api.Response;
import com.travels.travels_api.dto.auth.LoginRequestDTO;
import com.travels.travels_api.dto.auth.RegisterRequestDTO;
import com.travels.travels_api.entity.ApplicationUser;
import com.travels.travels_api.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Response<UsernamePasswordAuthenticationToken>> login(@RequestBody LoginRequestDTO request) {
        UsernamePasswordAuthenticationToken result = authService.login(request);
        return ResponseEntity.ok(new Response<UsernamePasswordAuthenticationToken>(null, result));
    }

    @PostMapping("/register")
    public ResponseEntity<Response<ApplicationUser>> register(@RequestBody RegisterRequestDTO request) {
        ApplicationUser result = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<ApplicationUser>(null, result));
    }
}
