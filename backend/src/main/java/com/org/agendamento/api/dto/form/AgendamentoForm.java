package com.org.agendamento.api.dto.form;

import com.org.agendamento.domain.entity.enums.Horario;
import com.org.agendamento.domain.entity.enums.Turno;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record AgendamentoForm(

        @NotNull(message = "A sala é obrigatória")
        UUID sala,

        @NotNull(message = "A data é obrigatória")
        Date data,

        @NotNull(message = "A descricao é obrigatória")
        String descricao,

        @NotNull(message = "O turno é obrigatório")
        Turno turno,

        @NotNull(message = "O horário é obrigatório")
        Horario horario

) {
}
