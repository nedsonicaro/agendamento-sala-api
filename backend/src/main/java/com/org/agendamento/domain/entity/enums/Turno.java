package com.org.agendamento.domain.entity.enums;

import lombok.Getter;

@Getter
public enum Turno {

    MANHA("MANHA"),
    TARDE("TARDE"),
    NOITE("NOITE");

    private final String tipoTurno;

    Turno(String tipoTurno) {
        this.tipoTurno = tipoTurno;
    }
}
