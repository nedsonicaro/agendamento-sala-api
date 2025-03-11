package com.org.agendamento.api.exception;

public class AgendamentoInvalidoException extends RuntimeException {

    public AgendamentoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
