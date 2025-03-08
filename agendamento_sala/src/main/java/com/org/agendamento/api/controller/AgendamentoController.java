package com.org.agendamento.api.controller;

import com.org.agendamento.api.dto.form.AgendamentoForm;
import com.org.agendamento.api.dto.response.AgendamentoResponse;
import com.org.agendamento.api.mapper.AgendamentoMapper;
import com.org.agendamento.domain.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> obterPorId(@PathVariable UUID id) {

        var agendamento = agendamentoService.obterPorId(id);
        var agendamentoResponse = AgendamentoMapper.paraAgendamentoResponse(agendamento);

        return ResponseEntity.ok(agendamentoResponse);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> obterAgendamentos() {

        var agendamentos = agendamentoService.obterTodos();

        var agendamentosResponse = agendamentos
                .stream()
                .map(AgendamentoMapper::paraAgendamentoResponse)
                .toList();

        return agendamentosResponse.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(agendamentosResponse);
    }

    @PostMapping
    public void salvarAgendamento(@RequestBody @Valid AgendamentoForm form) {

        agendamentoService.salvarAgendamento(form);
    }

    @PutMapping("/{id}")
    public void editarAgendamento(@PathVariable UUID id,
                                  @RequestBody @Valid AgendamentoForm form) {

        var agendamento = agendamentoService.obterPorId(id);
        agendamentoService.editarAgendamento(agendamento, form);
    }

    @DeleteMapping("/{id}")
    public void deletarAgendamento(@PathVariable UUID id) {

        var agendamento = agendamentoService.obterPorId(id);
        agendamentoService.deletarAgendamento(agendamento);
    }
}
