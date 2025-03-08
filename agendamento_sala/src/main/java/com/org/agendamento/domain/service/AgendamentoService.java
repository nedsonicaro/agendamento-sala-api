package com.org.agendamento.domain.service;

import com.org.agendamento.api.dto.form.AgendamentoForm;
import com.org.agendamento.api.mapper.AgendamentoMapper;
import com.org.agendamento.domain.entity.Agendamento;
import com.org.agendamento.domain.entity.Sala;
import com.org.agendamento.domain.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return repository.findById(uuid).orElseThrow();
    }

    public List<Agendamento> obterTodos() {

        return repository.findAll();
    }

    @Transactional
    public void salvarAgendamento(AgendamentoForm form) {

        var sala = salaService.obterPorId(form.sala());
        var novoAgendamento = AgendamentoMapper.paraAgendamento(form, sala);

        repository.save(novoAgendamento);
    }

    @Transactional
    public void editarAgendamento(Agendamento agendamento,
                                  AgendamentoForm form) {

        var sala = salaService.obterPorId(form.sala());
        AgendamentoMapper.atualizarAgendamento(agendamento, sala, form);
    }

    @Transactional
    public void deletarAgendamento(Agendamento agendamento) {

        repository.delete(agendamento);
    }
}
