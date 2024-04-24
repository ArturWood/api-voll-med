package br.com.dev.api.voll.med.service;

import br.com.dev.api.voll.med.dto.paciente.PacientePageDto;
import br.com.dev.api.voll.med.dto.paciente.PacienteRequestDto;
import br.com.dev.api.voll.med.dto.paciente.PacienteUpdateDto;
import br.com.dev.api.voll.med.infra.exception.PacienteNotFoundException;
import br.com.dev.api.voll.med.model.paciente.Paciente;
import br.com.dev.api.voll.med.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public Paciente cadastrar(PacienteRequestDto dto) {
        Paciente paciente = new Paciente(dto);
        return pacienteRepository.save(paciente);
    }

    public Page<PacientePageDto> listar(Pageable paginacao) {
        Page<Paciente> allByAtivoTrue = pacienteRepository.findAllByAtivoTrue(paginacao);
        return allByAtivoTrue.map(PacientePageDto::new);
    }

    @Transactional
    public Paciente atualizar(PacienteUpdateDto dto) {
        Paciente paciente = this.getPaciente(dto.id());
        paciente.atualizaDados(dto);
        return paciente;
    }

    @Transactional
    public void excluir(Long id) {
        Paciente paciente = this.getPaciente(id);
        paciente.inativar();
    }

    public Paciente detalhar(Long id) {
        return this.getPaciente(id);
    }

    private Paciente getPaciente(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new PacienteNotFoundException("Não encontrado paciente com ID: " + id));
    }
}
