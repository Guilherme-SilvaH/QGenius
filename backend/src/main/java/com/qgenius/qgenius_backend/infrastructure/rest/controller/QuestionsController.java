package com.qgenius.qgenius_backend.infrastructure.rest.controller;

import com.qgenius.qgenius_backend.core.domain.entity.Questions;
import com.qgenius.qgenius_backend.core.domain.entity.User;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.ICreateQuestionsUseCase;
import com.qgenius.qgenius_backend.infrastructure.mapper.QuestionsWebMapper;
import com.qgenius.qgenius_backend.infrastructure.rest.request.QuestionsRequest;
import com.qgenius.qgenius_backend.infrastructure.rest.response.QuestionsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionsController {

    private final ICreateQuestionsUseCase createQuestionsUseCase;
    private final QuestionsWebMapper questionsWebMapper;

    @PostMapping
    public ResponseEntity<QuestionsResponse> createQuestions(
            @Valid @RequestBody QuestionsRequest request,
            @AuthenticationPrincipal User currentUser
    ) {
        Questions questionsDomain = questionsWebMapper.toDomain(request);
        Questions createdQuestions = createQuestionsUseCase.createQuestions(questionsDomain, currentUser);
        QuestionsResponse response = questionsWebMapper.toResponse(createdQuestions);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
