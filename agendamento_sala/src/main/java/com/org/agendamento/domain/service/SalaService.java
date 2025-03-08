package com.org.agendamento.domain.service;

import com.org.agendamento.api.dto.form.SalaForm;
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
        return repository.findById(uuid).orElseThrow();
    }

    public List<Sala> obterTodas() {
        return repository.findAll();
    }

    @Transactional
    public void salvarSala(SalaForm form) {

        var novaSala = SalaMapper.paraSala(form);
        repository.save(novaSala);
    }

    @Transactional
    public void editarSala(Sala sala,
                           SalaForm form) {

        SalaMapper.atualizarSala(sala, form);
    }

    @Transactional
    public void deletarSala(Sala sala) {

        validarSeSalaPossuiAgendamento(sala);
        repository.delete(sala);
    }

    private void validarSeSalaPossuiAgendamento(Sala sala) {

        var possui = agendamentoRepository.existsBySala(sala);

        if (possui) throw new RuntimeException();
    }
}
