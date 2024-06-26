package br.com.dev.api.voll.med.repository;

import br.com.dev.api.voll.med.model.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetweenAndMotivoCancelamentoIsNull(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
