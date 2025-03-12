package com.org.agendamento.api.dto.response;

import com.org.agendamento.domain.entity.enums.Status;
import lombok.Builder;

import java.util.UUID;

@Builder
public record SalaResponse(

        UUID uuid,
        String descricao,
        String andar,
        int capacidade,
        Status status
) {
}
