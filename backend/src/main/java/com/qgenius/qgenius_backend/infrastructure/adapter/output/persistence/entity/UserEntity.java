package com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private UUID id;

    private String name;
    private String email;
    private String passwordHash;
    private String plan;

    private LocalDateTime createdAt;
}