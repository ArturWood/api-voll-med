package br.com.dev.api.voll.med.model.consulta.validacoes.agendamento;

import br.com.dev.api.voll.med.dto.consulta.ConsultaRequestDto;
import br.com.dev.api.voll.med.infra.exception.ValidacaoAgendamentoException;
import br.com.dev.api.voll.med.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoComOutraConsultaNoMesmoHorario implements ValidadorDeAgendamento {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaRequestDto dto) {
        if (consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dto.idMedico(), dto.data()))
            throw new ValidacaoAgendamentoException("Médico já possui outra consulta agendada nesse mesmo horário");
    }
}
