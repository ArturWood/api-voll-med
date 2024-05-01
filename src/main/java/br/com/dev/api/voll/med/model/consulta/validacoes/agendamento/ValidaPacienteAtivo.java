package br.com.dev.api.voll.med.model.consulta.validacoes.agendamento;

import br.com.dev.api.voll.med.dto.consulta.ConsultaRequestDto;
import br.com.dev.api.voll.med.infra.exception.ValidacaoAgendamentoException;
import br.com.dev.api.voll.med.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteAtivo implements ValidadorDeAgendamento {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validar(ConsultaRequestDto dto) {
        if (!pacienteRepository.existsByAtivoTrueAndId(dto.idPaciente()))
            throw new ValidacaoAgendamentoException("Consulta não pode ser agendada com paciente excluído");
    }
}
