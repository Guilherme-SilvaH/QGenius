package com.qgenius.qgenius_backend.infrastructure.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneratedQuestion {
    private String question;
    private String context;
    private List<String> options;
    private String correctAnswer;
}
