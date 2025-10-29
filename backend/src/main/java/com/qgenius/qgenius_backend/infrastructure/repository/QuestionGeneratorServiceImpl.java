package com.qgenius.qgenius_backend.infrastructure.repository;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IQuestionGeneratorService;
import com.qgenius.qgenius_backend.infrastructure.client.IAServiceClient;
import com.qgenius.qgenius_backend.infrastructure.client.dto.GenerateQuestionsRequest;
import com.qgenius.qgenius_backend.infrastructure.client.dto.GeneratedQuestion;
import com.qgenius.qgenius_backend.infrastructure.mapper.IAServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionGeneratorServiceImpl implements IQuestionGeneratorService {

    private final IAServiceClient iaServiceClient;
    private final IAServiceMapper iaServiceMapper;

    @Override
    public List<Questions> generateQuestions(String theme, String difficulty, String type, Integer quantity, UUID userId) {
        GenerateQuestionsRequest request = GenerateQuestionsRequest.builder()
                .theme(theme)
                .difficulty(difficulty)
                .type(type)
                .quantity(quantity)
                .build();

        List<GeneratedQuestion> generated = iaServiceClient.generateQuestions(request);

        return generated.stream()
                .map(gq -> {
                    Questions q = iaServiceMapper.toDomain(gq);
                    q.setIdUser(userId);
                    return q;
                })
                .toList();
    }
}