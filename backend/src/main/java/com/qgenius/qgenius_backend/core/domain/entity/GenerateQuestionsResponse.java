package com.qgenius.qgenius_backend.core.domain.entity;

import com.qgenius.qgenius_backend.infrastructure.client.dto.GeneratedQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateQuestionsResponse {
    private List<GeneratedQuestion> questions;
    private String status;
}
