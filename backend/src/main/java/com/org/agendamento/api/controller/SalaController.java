package com.org.agendamento.api.controller;

import com.org.agendamento.api.dto.form.SalaForm;
import com.org.agendamento.api.dto.response.SalaResponse;
import com.org.agendamento.api.mapper.SalaMapper;
import com.org.agendamento.domain.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/sala")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponse> obterPorId(@PathVariable UUID id) {

        var sala = salaService.obterPorId(id);
        var salaResponse = SalaMapper.paraSalaResponse(sala);

        return ResponseEntity.ok(salaResponse);
    }

    @GetMapping()
    public ResponseEntity<List<SalaResponse>> obterTodas() {

        var salas = salaService.obterTodas();

        var salasResponse = salas
                .stream()
                .map(SalaMapper::paraSalaResponse)
                .toList();

        return salasResponse.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(salasResponse);
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<SalaResponse>> obterTodasAtivas() {

        var salas = salaService.obterTodasAtivas();

        var salasResponse = salas
                .stream()
                .map(SalaMapper::paraSalaResponse)
                .toList();

        return salasResponse.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(salasResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarSala(@RequestBody @Valid SalaForm form) {

        salaService.salvarSala(form);
    }

    @PutMapping("/{id}")
    public void editarSala(@PathVariable UUID id,
                           @RequestBody @Valid SalaForm form) {

        var sala = salaService.obterPorId(id);
        salaService.editarSala(sala, form);
    }

    @DeleteMapping("/{id}")
    public void deletarSala(@PathVariable UUID id) {

        var sala = salaService.obterPorId(id);
        salaService.deletarSala(sala);
    }
}
