package com.qgenius.qgenius_backend.infrastructure.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
    @Type(JsonBinaryType.class)
    private String questions;

    @OneToMany(mappedBy = "generation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<QuestionsEntity> questionsList = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = "PENDING";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
