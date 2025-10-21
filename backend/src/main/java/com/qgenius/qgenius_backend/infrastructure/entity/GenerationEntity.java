package com.qgenius.qgenius_backend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "generations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerationEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "theme", nullable = false, length = 255)
    private String theme;

    @Column(name = "difficulty", length = 50)
    private String difficulty;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "questions", columnDefinition = "jsonb")
    private String questions; // JSONB armazenado como String

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}
