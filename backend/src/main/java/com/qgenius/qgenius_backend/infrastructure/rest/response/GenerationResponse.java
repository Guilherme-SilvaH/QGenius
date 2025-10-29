package com.qgenius.qgenius_backend.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerationResponse {
    private UUID id;
    private String theme;
    private String difficulty;
    private String type;
    private Integer quantity;
    private String status;
    private List<QuestionsResponse> questions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
