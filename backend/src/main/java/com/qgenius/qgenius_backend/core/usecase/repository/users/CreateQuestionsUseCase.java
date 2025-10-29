package com.qgenius.qgenius_backend.core.usecase.repository.users;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.domain.exception.NotQuestionsExistsException;
import com.qgenius.qgenius_backend.core.domain.valueobject.Email;
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
        String questionEmailUser = user.getEmail().value();
        UUID questionIdUser = user.getId();
        log.info("Criando perguntas para o usuário de Email: {}", questionEmailUser);

        if (questions.getQuestion() == null || questions.getQuestion().isBlank()) {
            log.warn("Tentativa de criar perguntas vazias para o usuário de ID: {}", questionIdUser);
            throw new NotQuestionsExistsException();
        }

        if (questions.getThemesCustoms() != null && questions.getThemesCustoms().getCustomThemes() != null
                && !questions.getThemesCustoms().getCustomThemes().isBlank()) {
            log.info("Usando tema personalizado: {}", questions.getThemesCustoms().getCustomThemes());
            return iQuestionsRepository.saveQuestions(questions, user, null);
        }

        if (questions.getThemes() != null && questions.getThemes().getCode() != null
                && !questions.getThemes().getCode().isBlank()) {
            ThemeEntity theme = themeJpaRepository.findByCode(questions.getThemes().getCode())
                    .orElseThrow(() -> new IllegalArgumentException("Tema não encontrado: " + questions.getThemes().getCode()));
            log.info("Usando tema padrão: {}", theme.getDescricao());
            return iQuestionsRepository.saveQuestions(questions, user, theme);
        }

        throw new IllegalArgumentException("É necessário informar um tema ou um tema personalizado.");
    }
}
