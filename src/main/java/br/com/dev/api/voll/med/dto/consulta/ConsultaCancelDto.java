package br.com.dev.api.voll.med.dto.consulta;

import br.com.dev.api.voll.med.model.consulta.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record ConsultaCancelDto(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivo
) {
}
