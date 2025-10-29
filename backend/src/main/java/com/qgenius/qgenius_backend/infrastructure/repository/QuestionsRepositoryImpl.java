package com.qgenius.qgenius_backend.infrastructure.repository;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IQuestionsRepository;
import com.qgenius.qgenius_backend.infrastructure.entity.QuestionsEntity;
import com.qgenius.qgenius_backend.infrastructure.entity.ThemeEntity;
import com.qgenius.qgenius_backend.infrastructure.entity.UserEntity;
import com.qgenius.qgenius_backend.infrastructure.mapper.QuestionsMapper;
import com.qgenius.qgenius_backend.infrastructure.repository.interfaces.QuestionsJpaRepository;
import com.qgenius.qgenius_backend.infrastructure.repository.interfaces.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class QuestionsRepositoryImpl implements IQuestionsRepository {

    private final QuestionsJpaRepository questionsJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final QuestionsMapper questionsMapper;

    @Override
    @Transactional
    public Questions saveQuestions(Questions questions, User user, ThemeEntity theme) {
        UUID userId = user.getId();
        UserEntity userEntity = userJpaRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        QuestionsEntity questionsEntity = questionsMapper.toEntity(questions);
        questionsEntity.setUser(userEntity);
        questionsEntity.setTheme(theme);

        if (questionsEntity.getCustomTheme() != null) {
            questionsEntity.getCustomTheme().setUser(userEntity);
        }

        QuestionsEntity savedEntity = questionsJpaRepository.save(questionsEntity);

        return questionsMapper.toDomain(savedEntity);
    }
}
