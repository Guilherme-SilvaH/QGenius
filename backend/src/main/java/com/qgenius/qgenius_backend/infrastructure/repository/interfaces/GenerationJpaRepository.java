package com.qgenius.qgenius_backend.infrastructure.repository.interfaces;

import com.qgenius.qgenius_backend.infrastructure.entity.GenerationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GenerationJpaRepository extends JpaRepository<GenerationEntity, UUID> {
    List<GenerationEntity> findByUserId(UUID userId);
}