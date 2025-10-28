package com.qgenius.qgenius_backend.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Themes {
    MATEMATICA("MATEMATICA", "Matemática"),
    FISICA("FISICA", "Física"),
    QUIMICA("QUIMICA", "Química"),
    BIOLOGIA("BIOLOGIA", "Biologia"),
    HISTORIA("HISTORIA", "História"),
    GEOGRAFIA("GEOGRAFIA", "Geografia"),
    PORTUGUES("PORTUGUES", "Português"),
    LITERATURA("LITERATURA", "Literatura"),
    FILOSOFIA("FILOSOFIA", "Filosofia"),
    SOCIOLOGIA("SOCIOLOGIA", "Sociologia"),
    INFORMATICA("INFORMATICA", "Informática"),
    PROGRAMACAO("PROGRAMACAO", "Programação"),
    INGLES("INGLES", "Inglês"),
    ATUALIDADES("ATUALIDADES", "Atualidades"),
    ARTE("ARTE", "Arte"),
    EDUCACAO_FISICA("EDUCACAO_FISICA", "Educação Física"),
    ECONOMIA("ECONOMIA", "Economia"),
    DIREITO("DIREITO", "Direito"),
    PSICOLOGIA("PSICOLOGIA", "Psicologia"),
    MEDICINA("MEDICINA", "Medicina"),
    ENGENHARIA("ENGENHARIA", "Engenharia"),
    ASTRONOMIA("ASTRONOMIA", "Astronomia"),
    MEIO_AMBIENTE("MEIO_AMBIENTE", "Meio Ambiente"),
    TECNOLOGIA("TECNOLOGIA", "Tecnologia"),
    INTELIGENCIA_ARTIFICIAL("INTELIGENCIA_ARTIFICIAL", "Inteligência Artificial");

    private final String code;
    private final String descricao;
}

