package com.qgenius.qgenius_backend.core.usecase.repository.interfaces;

import com.qgenius.qgenius_backend.core.domain.entity.Themes;

import java.util.Optional;
import java.util.UUID;

public interface IThemeCustomRepository {
    Optional<Themes> findByCustomThemesAndUserId(String customThemes, UUID userId);
}
