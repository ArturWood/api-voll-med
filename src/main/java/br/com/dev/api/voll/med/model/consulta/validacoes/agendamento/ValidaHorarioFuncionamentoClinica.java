package br.com.dev.api.voll.med.model.consulta.validacoes.agendamento;

import br.com.dev.api.voll.med.dto.consulta.ConsultaRequestDto;
import br.com.dev.api.voll.med.infra.exception.ValidacaoAgendamentoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidaHorarioFuncionamentoClinica implements ValidadorDeAgendamento {
    @Override
    public void validar(ConsultaRequestDto dto) {
        LocalDateTime dataConsulta = dto.data();
        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        boolean depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;
        if (domingo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica)
            throw new ValidacaoAgendamentoException("Consulta fora do horário de funcionamento da clínica");
    }
}
