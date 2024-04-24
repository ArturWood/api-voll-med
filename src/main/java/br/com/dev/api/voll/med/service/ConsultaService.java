package br.com.dev.api.voll.med.service;

import br.com.dev.api.voll.med.dto.consulta.ConsultaCancelDto;
import br.com.dev.api.voll.med.dto.consulta.ConsultaRequestDto;
import br.com.dev.api.voll.med.infra.exception.ConsultaNotFoundException;
import br.com.dev.api.voll.med.infra.exception.ValidacaoAgendamentoException;
import br.com.dev.api.voll.med.model.consulta.Consulta;
import br.com.dev.api.voll.med.model.consulta.validacoes.agendamento.ValidadorDeAgendamento;
import br.com.dev.api.voll.med.model.consulta.validacoes.cancelamento.ValidadorDeCancelamento;
import br.com.dev.api.voll.med.model.medico.Medico;
import br.com.dev.api.voll.med.model.paciente.Paciente;
import br.com.dev.api.voll.med.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private List<ValidadorDeAgendamento> validadorDeAgendamento;
    @Autowired
    private List<ValidadorDeCancelamento> validadorDeCancelamento;

    @Transactional
    public Consulta agendar(ConsultaRequestDto dto) {
        this.validaIds(dto);
        validadorDeAgendamento.forEach(v -> v.validar(dto));
        Paciente paciente = pacienteService.detalhar(dto.idPaciente());
        Medico medico = this.escolheMedico(dto);
        Consulta consulta = new Consulta(medico, paciente, dto.data());
        return consultaRepository.save(consulta);
    }

    @Transactional
    public void cancelar(ConsultaCancelDto dto) {
        Consulta consulta = this.getConsulta(dto.idConsulta());
        validadorDeCancelamento.forEach(v -> v.validar(dto));
        consulta.cancelar(dto.motivo());
    }

    public Consulta detalhar(Long id) {
        return this.getConsulta(id);
    }

    private Consulta getConsulta(Long id) {
        return consultaRepository.findById(id).orElseThrow(() -> new ConsultaNotFoundException("Não encontrada consulta com ID: " + id));
    }

    private void validaIds(ConsultaRequestDto dto) {
        pacienteService.exists(dto.idPaciente());
        if (dto.idMedico() != null)
            medicoService.exists(dto.idMedico());
    }

    private Medico escolheMedico(ConsultaRequestDto dto) {
        if (dto.idMedico() != null)
            return medicoService.detalhar(dto.idMedico());
        if (dto.especialidade() == null)
            throw new ValidacaoAgendamentoException("Especialidade obrigatoria quando ID do medico nao informado");
        Medico medico = medicoService.escolheMedicoLivre(dto.especialidade(), dto.data());
        if (medico == null)
            throw new ValidacaoAgendamentoException("Nenhum medico disponivel na data e horario escolhido");
        return medico;
    }
}
