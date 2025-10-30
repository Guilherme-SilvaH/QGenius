package com.qgenius.qgenius_backend.infrastructure.repository.interfaces;

import com.qgenius.qgenius_backend.infrastructure.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ThemeCustomJPARepository extends JpaRepository<ThemeEntity, UUID> {
    Optional<ThemeEntity> findByCustomThemesAndUserId(String customThemes, UUID userId);
}
