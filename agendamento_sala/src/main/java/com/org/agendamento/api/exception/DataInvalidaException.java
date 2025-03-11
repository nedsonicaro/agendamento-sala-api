package com.org.agendamento.api.exception;

public class DataInvalidaException extends RuntimeException {

    public DataInvalidaException(String mensagem) {
        super(mensagem);
    }
}
