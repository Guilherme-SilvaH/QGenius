package com.qgenius.qgenius_backend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "questions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "question", nullable = false, length = 500)
    private String question;

    @Column(name = "context", columnDefinition = "TEXT")
    private String context;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private ThemeEntity theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_theme_id")
    private ThemesCustomsEntity customTheme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id")
    private GenerationEntity generation;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}