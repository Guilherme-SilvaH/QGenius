package com.qgenius.qgenius_backend.core.usecase.repository.interfaces;

import com.qgenius.qgenius_backend.core.domain.entity.Generation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IGenerationRepository {

    Generation save(Generation generation);

    Optional<Generation> findById(UUID id);

    List<Generation> findByUserId(UUID userId);

    void deleteById(UUID id);
}