package com.qgenius.qgenius_backend.infrastructure.repository.interfaces;


import com.qgenius.qgenius_backend.infrastructure.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ThemeJpaRepository extends JpaRepository<ThemeEntity, UUID> {
    Optional<ThemeEntity> findByCode(String code);
}