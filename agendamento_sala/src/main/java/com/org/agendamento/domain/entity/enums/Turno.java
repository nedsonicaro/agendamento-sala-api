package com.org.agendamento.domain.entity.enums;

import lombok.Getter;

@Getter
public enum Turno {

    MANHA("Manhã"),
    TARDE("Tarde"),
    NOITE("Noite");

    private final String tipoTurno;

    Turno(String tipoTurno) {
        this.tipoTurno = tipoTurno;
    }
}
