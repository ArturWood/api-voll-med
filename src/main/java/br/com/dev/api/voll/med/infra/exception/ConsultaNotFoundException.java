package br.com.dev.api.voll.med.infra.exception;

public class ConsultaNotFoundException extends RuntimeException {
    public ConsultaNotFoundException(String message) {
        super(message);
    }
}
