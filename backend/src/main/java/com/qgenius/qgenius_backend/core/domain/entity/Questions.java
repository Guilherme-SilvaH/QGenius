package com.qgenius.qgenius_backend.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Questions {
    private UUID id;
    private String question;
    private String context;
    private Themes themes;
    private ThemesCustoms themesCustoms;
    private UUID idUser;
}
