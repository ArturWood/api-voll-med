package br.com.dev.api.voll.med.infra.exception;

import br.com.dev.api.voll.med.infra.exception.dto.ValidationErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(MedicoNotFoundException.class)
    public ResponseEntity handleMedicoNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity handlePacienteNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ValidationErrorResponseDto::new).toList());
    }
}
