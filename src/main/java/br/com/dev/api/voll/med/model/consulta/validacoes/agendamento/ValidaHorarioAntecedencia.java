package br.com.dev.api.voll.med.model.consulta.validacoes.agendamento;

import br.com.dev.api.voll.med.dto.consulta.ConsultaRequestDto;
import br.com.dev.api.voll.med.infra.exception.ValidacaoAgendamentoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidaHorarioAntecedenciaAgendamento")
public class ValidaHorarioAntecedencia implements ValidadorDeAgendamento {
    @Override
    public void validar(ConsultaRequestDto dto) {
        LocalDateTime dataConsulta = dto.data();
        LocalDateTime agora = LocalDateTime.now();
        long diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
        if (diferencaEmMinutos < 30)
            throw new ValidacaoAgendamentoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
    }
}
