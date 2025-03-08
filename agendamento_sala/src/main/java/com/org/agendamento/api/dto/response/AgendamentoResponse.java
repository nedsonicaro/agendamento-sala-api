package com.org.agendamento.api.dto.response;

import com.org.agendamento.domain.entity.enums.Horario;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record AgendamentoResponse(

        UUID id,
        SalaResponse sala,
        Date data,
        String descricao,
        String turno,
        Horario horario

) {
}
