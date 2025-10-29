package com.qgenius.qgenius_backend.infrastructure.rest.request;

import com.qgenius.qgenius_backend.core.domain.entity.Themes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsRequest {

    @NotBlank(message = "Question is required")
    @Size(max = 500, message = "Question must be at most 500 characters")
    private String question;

    @Size(max = 2000, message = "Context must be at most 2000 characters")
    private String context;

    private Themes themes;

    @Size(max = 255, message = "Custom theme must be at most 255 characters")
    private String customTheme;
}
