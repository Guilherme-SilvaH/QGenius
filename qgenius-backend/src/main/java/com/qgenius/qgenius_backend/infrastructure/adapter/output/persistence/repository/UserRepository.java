package com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.repository;

import com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}