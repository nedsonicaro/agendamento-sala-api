package com.org.agendamento.api.exception;

public class SalaInvalidaException extends RuntimeException {

    public SalaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
