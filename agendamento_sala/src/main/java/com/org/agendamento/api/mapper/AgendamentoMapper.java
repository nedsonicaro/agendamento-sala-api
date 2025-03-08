package com.org.agendamento.api.mapper;

import com.org.agendamento.api.dto.form.AgendamentoForm;
import com.org.agendamento.api.dto.response.AgendamentoResponse;
import com.org.agendamento.domain.entity.Agendamento;
import com.org.agendamento.domain.entity.Sala;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgendamentoMapper {

    public static AgendamentoResponse paraAgendamentoResponse(Agendamento agendamento) {
        return isNull(agendamento) ? null :
                AgendamentoResponse
                        .builder()
                        .id(agendamento.getId())
                        .sala(SalaMapper.paraSalaResponse(agendamento.getSala()))
                        .data(agendamento.getData())
                        .descricao(agendamento.getDescricao())
                        .turno(agendamento.getTurno().getTipoTurno())
                        .horario(agendamento.getHorario())
                        .build();
    }

    public static Agendamento paraAgendamento(AgendamentoForm form,
                                              Sala sala) {

        return Agendamento
                .builder()
                .sala(sala)
                .data(form.data())
                .descricao(form.descricao().trim())
                .turno(form.turno())
                .horario(form.horario())
                .build();
    }

    public static void atualizarAgendamento(Agendamento agendamento,
                                            Sala sala,
                                            AgendamentoForm form) {

        agendamento.setSala(sala);
        agendamento.setData(form.data());
        agendamento.setTurno(form.turno());
        agendamento.setDescricao(form.descricao().trim());
        agendamento.setHorario(form.horario());
    }
}
