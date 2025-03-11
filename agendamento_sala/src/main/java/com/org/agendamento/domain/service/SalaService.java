package com.org.agendamento.domain.service;

import com.org.agendamento.api.dto.form.SalaForm;
import com.org.agendamento.api.exception.SalaAgendadaException;
import com.org.agendamento.api.exception.SalaInvalidaException;
import com.org.agendamento.api.exception.SalaNaoEncontradaException;
import com.org.agendamento.api.mapper.SalaMapper;
import com.org.agendamento.domain.entity.Sala;
import com.org.agendamento.domain.repository.AgendamentoRepository;
import com.org.agendamento.domain.repository.SalaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SalaService {

    private final SalaRepository repository;
    private final AgendamentoRepository agendamentoRepository;

    public SalaService(SalaRepository repository,
                       AgendamentoRepository agendamentoRepository) {
        this.repository = repository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public Sala obterPorId(UUID uuid) {
        return repository.findById(uuid).orElseThrow(SalaNaoEncontradaException::new);
    }

    public List<Sala> obterTodas() {
        return repository.findAll();
    }

    public List<Sala> obterTodasAtivas() {
        return repository.obterTodasAtivas();
    }

    @Transactional
    public void salvarSala(SalaForm form) {

        checarEValidarDuplicidadeDeSalaPorAndar(form);

        var novaSala = SalaMapper.paraSala(form);
        repository.save(novaSala);
    }

    @Transactional
    public void editarSala(Sala sala,
                           SalaForm form) {

        validarAlteracaoDeStatusDaSala(sala, form);
        checarEValidarDuplicidadeDeSalaPorAndarAoAtualizar(sala, form);

        SalaMapper.atualizarSala(sala, form);
    }

    @Transactional
    public void deletarSala(Sala sala) {

        validarSeSalaPossuiAgendamento(sala);
        repository.delete(sala);
    }

    private void validarSeSalaPossuiAgendamento(Sala sala) {

        var possui = checarExistenciaDeAgendamentoPorSala(sala);

        if (possui)
            throw new SalaAgendadaException("Erro! Não é possível deletar uma sala com algum agendamento.");
    }

    private void validarAlteracaoDeStatusDaSala(Sala sala,
                                                SalaForm form) {

        if (sala.getStatus().equals(form.status())) return;

        var possui = checarExistenciaDeAgendamentoPorSala(sala);

        if (possui) throw new SalaAgendadaException(
                "Erro! Não é possível alterar o status de uma sala com algum agendamento."
        );
    }

    private void checarEValidarDuplicidadeDeSalaPorAndarAoAtualizar(Sala sala,
                                                                    SalaForm form) {

        if (sala.getDescricao().equalsIgnoreCase(form.descricao()) && sala.getAndar().equalsIgnoreCase(form.andar()))
            return;

        checarEValidarDuplicidadeDeSalaPorAndar(form);
    }

    private void checarEValidarDuplicidadeDeSalaPorAndar(SalaForm form) {

        var existeDuplicidade = repository.checarDuplicidadeDeSalaPorAndar(form.descricao(), form.andar());

        if (existeDuplicidade) throw new SalaInvalidaException("Erro! Já existe uma sala com essa descrição e andar.");
    }

    private boolean checarExistenciaDeAgendamentoPorSala(Sala sala) {
        return agendamentoRepository.existsBySala(sala);
    }
}
