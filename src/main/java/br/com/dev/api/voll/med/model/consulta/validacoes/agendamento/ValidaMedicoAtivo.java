package br.com.dev.api.voll.med.model.consulta.validacoes.agendamento;

import br.com.dev.api.voll.med.dto.consulta.ConsultaRequestDto;
import br.com.dev.api.voll.med.infra.exception.ValidacaoAgendamentoException;
import br.com.dev.api.voll.med.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoAtivo implements ValidadorDeAgendamento {
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(ConsultaRequestDto dto) {
        if (dto.idMedico() == null)
            return;
        if (!medicoRepository.existsByAtivoTrueAndId(dto.idMedico()))
            throw new ValidacaoAgendamentoException("Consulta não pode ser agendada com médico excluído");
    }
}
