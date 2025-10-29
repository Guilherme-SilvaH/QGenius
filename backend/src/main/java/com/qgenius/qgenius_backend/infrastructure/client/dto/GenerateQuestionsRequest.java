package com.qgenius.qgenius_backend.infrastructure.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateQuestionsRequest {
    private String theme;
    private String difficulty;
    private String type;
    private Integer quantity;
}
