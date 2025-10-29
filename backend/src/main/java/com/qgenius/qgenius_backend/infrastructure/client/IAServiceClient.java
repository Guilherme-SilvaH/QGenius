package com.qgenius.qgenius_backend.infrastructure.client;

import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IIAServiceClient;

import com.qgenius.qgenius_backend.infrastructure.client.dto.GenerateQuestionsRequest;
import com.qgenius.qgenius_backend.infrastructure.client.dto.GeneratedQuestion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@FeignClient(
        name = "ia-service",
        url = "${ia.service.url:http://localhost:3001}"
)
public interface IAServiceClient extends IIAServiceClient {
    @PostMapping("/api/generate")
    List<GeneratedQuestion> generateQuestions(@RequestBody GenerateQuestionsRequest request);
}
