package com.qgenius.qgenius_backend.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Themes {
    Matematica("Matemática"),
    Fisica("Física"),
    Quimica("Química"),
    Biologia("Biologia"),
    Historia("História"),
    Geografia("Geografia"),
    Portugues("Português"),
    Literatura("Literatura"),
    Filosofia("Filosofia"),
    Sociologia("Sociologia"),
    Informatica("Informática"),
    Programacao("Programação"),
    Ingles("Inglês"),
    Atualidades("Atualidades"),
    Arte("Arte"),
    EducacaoFisica("Educação Física"),
    Economia("Economia"),
    Direito("Direito"),
    Psicologia("Psicologia"),
    Medicina("Medicina"),
    Engenharia("Engenharia"),
    Astronomia("Astronomia"),
    MeioAmbiente("Meio Ambiente"),
    Tecnologia("Tecnologia"),
    InteligenciaArtificial("Inteligência Artificial");

    private final String descricao;
}
