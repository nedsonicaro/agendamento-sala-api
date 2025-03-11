package com.org.agendamento.domain.service;

import com.org.agendamento.api.dto.form.AgendamentoForm;
import com.org.agendamento.api.exception.AgendamentoInvalidoException;
import com.org.agendamento.api.exception.AgendamentoNaoEncontradoException;
import com.org.agendamento.api.exception.DataInvalidaException;
import com.org.agendamento.api.mapper.AgendamentoMapper;
import com.org.agendamento.domain.entity.Agendamento;
import com.org.agendamento.domain.entity.Sala;
import com.org.agendamento.domain.entity.enums.Status;
import com.org.agendamento.domain.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final SalaService salaService;

    public AgendamentoService(AgendamentoRepository repository,
                              SalaService salaService) {
        this.repository = repository;
        this.salaService = salaService;
    }

    public Agendamento obterPorId(UUID uuid) {
        return repository.findById(uuid).orElseThrow(AgendamentoNaoEncontradoException::new);
    }

    public List<Agendamento> obterTodosOrdenados() {

        return repository.obterTodosOrdenados();
    }

    @Transactional
    public void salvarAgendamento(AgendamentoForm form) {

        validarDataDeAgendamento(form);

        var sala = salaService.obterPorId(form.sala());
        validarSalaEExistenciaDeAgendamento(sala, form);

        var novoAgendamento = AgendamentoMapper.paraAgendamento(form, sala);
        repository.save(novoAgendamento);
    }

    @Transactional
    public void editarAgendamento(Agendamento agendamento,
                                  AgendamentoForm form) {

        validarDataDeAgendamento(form);
        var sala = salaService.obterPorId(form.sala());

        validarSalaEExistenciaDeAgendamentoAoAtualizar(sala, agendamento, form);
        AgendamentoMapper.atualizarAgendamento(agendamento, sala, form);
    }

    @Transactional
    public void deletarAgendamento(Agendamento agendamento) {

        repository.delete(agendamento);
    }

    private void validarSalaEExistenciaDeAgendamento(Sala sala,
                                                     AgendamentoForm form) {

        validarSeASalaEstaAtiva(sala);

        var existeAgendamento = repository.checarExistenciaDeAgendamentoDeSalaPorDataHorarioETurno(
                sala, form.data(), form.horario(), form.turno()
        );

        if (existeAgendamento) throw new AgendamentoInvalidoException(
                "Erro! Já existe um agendamento para essa sala, data, horário e turno."
        );
    }

    private void validarSalaEExistenciaDeAgendamentoAoAtualizar(Sala sala,
                                                                Agendamento agendamento,
                                                                AgendamentoForm form) {
        if (agendamento.getSala().equals(sala)
                && agendamento.getData().equals(form.data())
                && agendamento.getHorario().equals(form.horario())
                && agendamento.getTurno().equals(form.turno())) return;

        validarSeASalaEstaAtiva(sala);
        validarSalaEExistenciaDeAgendamento(sala, form);
    }

    private static void validarSeASalaEstaAtiva(Sala sala) {
        if (!sala.getStatus().equals(Status.ATIVA)) throw new AgendamentoInvalidoException(
                "Erro! Não é possível realizar um agendamento para uma sala que não está ativa."
        );
    }

    private static void validarDataDeAgendamento(AgendamentoForm form) {
        if (form.data().before(Date.valueOf(LocalDate.now())))
            throw new DataInvalidaException("Erro! Não é possível realizar um agendamento para uma data que já passou.");
    }
}
