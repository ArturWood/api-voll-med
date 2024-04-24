package br.com.dev.api.voll.med.model.consulta.validacoes.agendamento;

import br.com.dev.api.voll.med.dto.consulta.ConsultaRequestDto;
import br.com.dev.api.voll.med.infra.exception.ValidacaoAgendamentoException;
import br.com.dev.api.voll.med.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidaPacienteSemOutraConsultaNoDia implements ValidadorDeAgendamento {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaRequestDto dto) {
        LocalDateTime horarioAbertura = dto.data().withHour(7);
        LocalDateTime horarioFechamento = dto.data().withHour(18);
        if (consultaRepository.existsByPacienteIdAndDataBetweenAndMotivoCancelamentoIsNull(dto.idPaciente(), horarioAbertura, horarioFechamento))
            throw new ValidacaoAgendamentoException("Paciente já possui uma consulta agendada nesse dia");
    }
}
