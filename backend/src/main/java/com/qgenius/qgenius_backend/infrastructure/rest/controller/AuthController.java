package com.qgenius.qgenius_backend.infrastructure.rest.controller;

import com.qgenius.qgenius_backend.core.usecase.generation.IJwtService;;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IUserRepository;
import com.qgenius.qgenius_backend.infrastructure.rest.request.LoginRequest;
import com.qgenius.qgenius_backend.infrastructure.rest.request.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final IJwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado após autenticação"));
        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }
}