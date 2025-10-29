package com.qgenius.qgenius_backend.infrastructure.repository.interfaces;

import com.qgenius.qgenius_backend.infrastructure.entity.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionsJpaRepository extends JpaRepository<QuestionsEntity, UUID> {
}
