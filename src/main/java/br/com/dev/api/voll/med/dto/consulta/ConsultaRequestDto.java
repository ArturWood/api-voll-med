package br.com.dev.api.voll.med.dto.consulta;

import br.com.dev.api.voll.med.model.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequestDto(
        @NotNull
        Long idPaciente,
        Long idMedico,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade
) {
}
