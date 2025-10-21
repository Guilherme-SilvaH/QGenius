package com.qgenius.qgenius_backend.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "themes_customs")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemesCustomsEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String customThemes;
}
