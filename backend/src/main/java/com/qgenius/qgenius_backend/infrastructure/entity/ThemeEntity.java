package com.qgenius.qgenius_backend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "themes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThemeEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "code", nullable = false, unique = true, length = 100)
    private String code;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
}
