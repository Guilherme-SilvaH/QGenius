package com.qgenius.qgenius_backend.core.usecase.repository.interfaces;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.User;

public interface ICreateQuestionsUseCase {
    Questions createQuestions(Questions questions, User user);
}
