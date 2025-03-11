package com.org.agendamento.api.exception;

public class AgendamentoNaoEncontradoException extends RuntimeException {

    public AgendamentoNaoEncontradoException() {
        super("Erro! Não existe agendamento para o ID informado.");
    }
}
