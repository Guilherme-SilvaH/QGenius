package com.qgenius.qgenius_backend.infrastructure.rest.controller;

import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.ICreateUserUseCase;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IFindAllUsersUseCase;
import com.qgenius.qgenius_backend.infrastructure.mapper.UserWebMapper;
import com.qgenius.qgenius_backend.infrastructure.rest.request.SignupRequest;
import com.qgenius.qgenius_backend.infrastructure.rest.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final ICreateUserUseCase createUserUseCase;
    private final IFindAllUsersUseCase findAllUsersUseCase;
    private final UserWebMapper userWebMapper;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody SignupRequest request) {
        log.info("Received request to create user with email: {}", request.getEmail());
        User createdUser = createUserUseCase.createUser(userWebMapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(userWebMapper.toResponse(createdUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        log.info("Buscando todos os usuários cadastrados...");
        List<User> users = findAllUsersUseCase.findAllUsers();
        List<UserResponse> responses = users.stream()
                .map(userWebMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }
}
