package com.org.agendamento.domain.entity.enums;

import lombok.Getter;

@Getter
public enum Horario {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6);

    private final int tipoHorario;

    Horario(int tipoHorario) {
        this.tipoHorario = tipoHorario;
    }
}
