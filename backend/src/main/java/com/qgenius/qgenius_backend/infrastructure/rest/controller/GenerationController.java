package com.qgenius.qgenius_backend.infrastructure.rest.controller;

import com.qgenius.qgenius_backend.core.domain.entity.Generation;
// import com.qgenius.qgenius_backend.core.domain.entity.Questions; // Não é mais necessário aqui
import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.ICreateGenerationUseCase;
import com.qgenius.qgenius_backend.infrastructure.rest.request.GenerationRequest;
import com.qgenius.qgenius_backend.infrastructure.rest.response.GenerationResponse; // <-- 1. Importar o Response
import com.qgenius.qgenius_backend.infrastructure.mapper.GenerationWebMapper; // <-- 2. Importar o Mapper
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/generations")
@RequiredArgsConstructor
public class GenerationController {

    private final ICreateGenerationUseCase createGenerationUseCase;
    private final GenerationWebMapper generationWebMapper;

    @PostMapping
    public ResponseEntity<GenerationResponse> create(
            @Valid @RequestBody GenerationRequest request,
            @AuthenticationPrincipal User currentUser) {

        List<Questions> questionsList = createGenerationUseCase.generateQuestions(
                currentUser,
                request.getTheme(),
                request.getDifficulty(),
                request.getType(),
                request.getQuantity()
        );

        Generation generation = Generation.builder()
                .id(UUID.randomUUID())
                .user(currentUser)
                .theme(request.getTheme())
                .difficulty(request.getDifficulty())
                .type(request.getType())
                .quantity(request.getQuantity())
                .questionsList(questionsList)
                .status("COMPLETED")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        GenerationResponse response = generationWebMapper.toResponse(generation);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}