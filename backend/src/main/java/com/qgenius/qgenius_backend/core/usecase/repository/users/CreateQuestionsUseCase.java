package com.qgenius.qgenius_backend.core.usecase.repository.users;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.exception.NotQuestionsExistsException;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.ICreateQuestionsUseCase;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IQuestionsRepository;
import com.qgenius.qgenius_backend.infrastructure.entity.ThemeEntity;
import com.qgenius.qgenius_backend.infrastructure.repository.interfaces.ThemeJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CreateQuestionsUseCase implements ICreateQuestionsUseCase {

    private final IQuestionsRepository iQuestionsRepository;
    private final ThemeJpaRepository themeJpaRepository;

    @Override
    public Questions createQuestions(Questions questions, User user) {
        UUID questionIdUser = user.getId();
        log.info("Criando perguntas para o usuário de ID: {}", questionIdUser);

        if (questions.getQuestion() == null || questions.getQuestion().isBlank()) {
            log.warn("Tentativa de criar perguntas vazias para o usuário de ID: {}", questionIdUser);
            throw new NotQuestionsExistsException();
        }

        ThemeEntity theme = themeJpaRepository.findByCode(String.valueOf(questions.getThemes()))
                .orElseThrow(() -> new IllegalArgumentException("Tema não encontrado: " + questions.getQuestion()));

        return iQuestionsRepository.saveQuestions(questions, user, theme);
    }
}
