package br.com.dev.api.voll.med.infra.exception.dto;

import org.springframework.validation.FieldError;

public record ValidationErrorResponseDto(
        String campo,
        String mensagem
) {
    public ValidationErrorResponseDto(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
