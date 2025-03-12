package com.org.agendamento.domain.entity.enums;

import lombok.Getter;

@Getter
public enum Status {

    ATIVA(0),
    INATIVA(1),
    EM_MANUTENCAO(2);

    private final int tipoStatus;

    Status(int tipoStatus) {
        this.tipoStatus = tipoStatus;
    }
}
