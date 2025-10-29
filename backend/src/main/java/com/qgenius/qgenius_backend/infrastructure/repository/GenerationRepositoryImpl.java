package com.qgenius.qgenius_backend.infrastructure.repository;

import com.qgenius.qgenius_backend.core.domain.entity.Generation;
import com.qgenius.qgenius_backend.core.usecase.repository.interfaces.IGenerationRepository;
import com.qgenius.qgenius_backend.infrastructure.entity.GenerationEntity;
import com.qgenius.qgenius_backend.infrastructure.mapper.GenerationMapper;
import com.qgenius.qgenius_backend.infrastructure.repository.interfaces.GenerationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GenerationRepositoryImpl implements IGenerationRepository {

    private final GenerationJpaRepository jpaRepository;
    private final GenerationMapper mapper;

    @Override
    public Generation save(Generation generation) {
        GenerationEntity entity = mapper.toEntity(generation);

        GenerationEntity saved = jpaRepository.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Generation> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Generation> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
