package com.qgenius.qgenius_backend.core.usecase.repository.interfaces;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.User;

import java.util.List;

public interface ICreateGenerationUseCase {
    List<Questions> generateQuestions(User user, String theme, String difficulty, String type, Integer quantity);
}
