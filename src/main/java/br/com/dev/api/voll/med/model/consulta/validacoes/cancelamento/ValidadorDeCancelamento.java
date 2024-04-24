package br.com.dev.api.voll.med.model.consulta.validacoes.cancelamento;

import br.com.dev.api.voll.med.dto.consulta.ConsultaCancelDto;

public interface ValidadorDeCancelamento {
    void validar(ConsultaCancelDto dto);
}
