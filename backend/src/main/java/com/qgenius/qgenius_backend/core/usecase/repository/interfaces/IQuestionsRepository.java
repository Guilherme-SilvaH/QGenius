package com.qgenius.qgenius_backend.core.usecase.repository.interfaces;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.infrastructure.entity.ThemeEntity;

public interface IQuestionsRepository {
    Questions saveQuestions(Questions questions, User user, ThemeEntity theme);
}
