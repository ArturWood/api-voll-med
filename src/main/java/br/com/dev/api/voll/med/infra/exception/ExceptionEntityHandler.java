package br.com.dev.api.voll.med.infra.exception;

import br.com.dev.api.voll.med.infra.exception.dto.ErrorResponseDto;
import br.com.dev.api.voll.med.infra.exception.dto.ValidationErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleSQLIntegrityConstraintViolation() {
        return ResponseEntity.badRequest().body(new ErrorResponseDto("Email ou CRM-CPF ja cadastrados"));
    }

    @ExceptionHandler(MedicoNotFoundException.class)
    public ResponseEntity handleMedicoNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity handlePacienteNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ConsultaNotFoundException.class)
    public ResponseEntity handleConsultaNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ValidacaoAgendamentoException.class)
    public ResponseEntity<ErrorResponseDto> handleValidacaoAgendamento(ValidacaoAgendamentoException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(ValidacaoCancelamentoException.class)
    public ResponseEntity handleValidacaoCancelamento(ValidacaoCancelamentoException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ValidationErrorResponseDto::new).toList());
    }
}
