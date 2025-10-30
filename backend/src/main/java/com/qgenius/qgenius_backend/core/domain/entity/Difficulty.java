package com.qgenius.qgenius_backend.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Difficulty {
    EASY, MEDIUM, HARD;

    public static Difficulty fromPortuguese(String pt) {
        return switch (pt.toUpperCase()) {
            case "FÁCIL", "FACIL" -> EASY;
            case "MÉDIO", "MEDIO" -> MEDIUM;
            case "DIFÍCIL", "DIFICIL" -> HARD;
            default -> MEDIUM;
        };
    }
}
