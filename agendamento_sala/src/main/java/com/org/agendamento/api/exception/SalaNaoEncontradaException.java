package com.org.agendamento.api.exception;

public class SalaNaoEncontradaException extends RuntimeException {

    public SalaNaoEncontradaException() {
        super("Erro! Não existe sala para o ID informado.");
    }
}
