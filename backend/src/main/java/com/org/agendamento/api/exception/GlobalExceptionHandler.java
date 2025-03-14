package com.org.agendamento.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AgendamentoInvalidoException.class})
    public ResponseEntity<Object> handleAgendamentoInvalidoException(AgendamentoInvalidoException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({AgendamentoNaoEncontradoException.class})
    public ResponseEntity<Object> handleAgendamentoNaoEncontradoException(AgendamentoNaoEncontradoException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({DataInvalidaException.class})
    public ResponseEntity<Object> handleDataInvalidaException(DataInvalidaException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({SalaAgendadaException.class})
    public ResponseEntity<Object> handleSalaAgendadaException(SalaAgendadaException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({SalaInvalidaException.class})
    public ResponseEntity<Object> handleSalaInvalidaException(SalaInvalidaException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({SalaNaoEncontradaException.class})
    public ResponseEntity<Object> handleSalaNaoEncontradaException(SalaNaoEncontradaException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    public ResponseEntity<Object> handleInternalServerErrorException(HttpServerErrorException.InternalServerError exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro inesperado. Por favor contate um administrador do sistema.");
    }
}
