package com.qgenius.qgenius_backend.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class ThemesCustoms {
    private UUID id;
    private String customThemes;

    public ThemesCustoms() {}

    public ThemesCustoms(String customThemes) {
        this.id = UUID.randomUUID();
        this.customThemes = customThemes;
    }

}
