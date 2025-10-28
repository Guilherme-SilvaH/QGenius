package com.qgenius.qgenius_backend.infrastructure.rest.response;

import com.qgenius.qgenius_backend.core.domain.entity.Themes;
import com.qgenius.qgenius_backend.core.domain.entity.ThemesCustoms;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsResponse {
    private UUID id;
    private String question;
    private String context;
    private Themes themes;
    private ThemesCustoms themesCustoms;
    private UUID idUser;
    private LocalDateTime createdAt;
}
