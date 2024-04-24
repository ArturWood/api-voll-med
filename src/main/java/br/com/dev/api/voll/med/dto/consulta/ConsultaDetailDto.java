package br.com.dev.api.voll.med.dto.consulta;

import br.com.dev.api.voll.med.model.consulta.Consulta;

import java.time.LocalDateTime;

public record ConsultaDetailDto(
        Long id,
        Long idPaciente,
        Long idMedico,
        LocalDateTime data
) {
    public ConsultaDetailDto(Consulta consulta) {
        this(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getData());
    }
}
