package com.org.agendamento.domain.entity.enums;

import lombok.Getter;

@Getter
public enum Status {

    ATIVA(1),
    INATIVA(2),
    EM_MANUTENCAO(3);

    private final int tipoStatus;

    Status(int tipoStatus) {
        this.tipoStatus = tipoStatus;
    }
}
