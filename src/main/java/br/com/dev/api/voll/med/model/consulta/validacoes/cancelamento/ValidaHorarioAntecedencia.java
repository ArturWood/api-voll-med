package br.com.dev.api.voll.med.model.consulta.validacoes.cancelamento;

import br.com.dev.api.voll.med.dto.consulta.ConsultaCancelDto;
import br.com.dev.api.voll.med.infra.exception.ValidacaoCancelamentoException;
import br.com.dev.api.voll.med.model.consulta.Consulta;
import br.com.dev.api.voll.med.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidaHorarioAntecedenciaCancelamento")
public class ValidaHorarioAntecedencia implements ValidadorDeCancelamento {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaCancelDto dto) {
        Consulta consulta = consultaRepository.getReferenceById(dto.idConsulta());
        LocalDateTime agora = LocalDateTime.now();
        long diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();
        if (diferencaEmHoras < 24)
            throw new ValidacaoCancelamentoException("Consulta somente pode ser cancelada com antecedência mínima de 24h");
    }
}
