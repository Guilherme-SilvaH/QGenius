package com.qgenius.qgenius_backend.core.usecase.repository.users;

import com.qgenius.qgenius_backend.core.domain.entity.Generation;
import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.ICreateGenerationUseCase;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IGenerationRepository;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IQuestionGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateGenerationUseCase implements ICreateGenerationUseCase {

    private final IGenerationRepository generationRepository;
    private final IQuestionGeneratorService questionGeneratorService;

    @Override
    @Transactional
    public List<Questions> generateQuestions(User user, String theme, String difficulty, String type, Integer quantity) {

        log.info("Creating generation for user {} with theme: {}", user.getId(), theme);

        Generation generation = Generation.builder()
                .id(UUID.randomUUID())
                .user(user)
                .theme(theme)
                .difficulty(difficulty)
                .type(type)
                .quantity(quantity)
                .status("PENDING")
                .questionsList(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Generation savedGeneration = generationRepository.save(generation);

        try {
            log.info("Generating {} questions via IA service", quantity);

            List<Questions> questions = questionGeneratorService.generateQuestions(
                    theme, difficulty, type, quantity, user.getId()
            );

            questions.forEach(q -> q.setGeneration(savedGeneration));

            savedGeneration.setQuestionsList(questions);
            savedGeneration.setStatus("COMPLETED");
            savedGeneration.setUpdatedAt(LocalDateTime.now());

            log.info("Generation {} completed with {} questions", savedGeneration.getId(), questions.size());

            return generationRepository.save(savedGeneration).getQuestionsList();

        } catch (Exception e) {
            log.error("Failed to generate questions for generation {}", savedGeneration.getId(), e);

            savedGeneration.setStatus("FAILED");
            savedGeneration.setUpdatedAt(LocalDateTime.now());
            generationRepository.save(savedGeneration);

            throw new RuntimeException("Failed to generate questions: " + e.getMessage(), e);
        }
    }
}