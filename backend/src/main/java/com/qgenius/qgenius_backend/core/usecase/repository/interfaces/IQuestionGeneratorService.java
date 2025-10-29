package com.qgenius.qgenius_backend.core.usecase.repository.interfaces;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import java.util.List;
import java.util.UUID;

public interface IQuestionGeneratorService {
    List<Questions> generateQuestions(String theme, String difficulty, String type, Integer quantity, UUID userId);
}