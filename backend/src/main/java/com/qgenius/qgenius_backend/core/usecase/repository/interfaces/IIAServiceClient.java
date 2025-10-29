package com.qgenius.qgenius_backend.core.usecase.repository.interfaces;

import com.qgenius.qgenius_backend.infrastructure.client.dto.GenerateQuestionsRequest;
import com.qgenius.qgenius_backend.infrastructure.client.dto.GeneratedQuestion;

import java.util.List;

public interface IIAServiceClient {
    List<GeneratedQuestion> generateQuestions(GenerateQuestionsRequest request);
}
