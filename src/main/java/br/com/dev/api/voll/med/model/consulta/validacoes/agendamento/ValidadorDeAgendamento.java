package br.com.dev.api.voll.med.model.consulta.validacoes.agendamento;

import br.com.dev.api.voll.med.dto.consulta.ConsultaRequestDto;

public interface ValidadorDeAgendamento {
    void validar(ConsultaRequestDto dto);
}
