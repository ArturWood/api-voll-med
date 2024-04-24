package br.com.dev.api.voll.med.service;

import br.com.dev.api.voll.med.dto.medico.MedicoPageDto;
import br.com.dev.api.voll.med.dto.medico.MedicoRequestDto;
import br.com.dev.api.voll.med.dto.medico.MedicoUpdateDto;
import br.com.dev.api.voll.med.infra.exception.MedicoNotFoundException;
import br.com.dev.api.voll.med.model.medico.Especialidade;
import br.com.dev.api.voll.med.model.medico.Medico;
import br.com.dev.api.voll.med.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    public Medico cadastrar(MedicoRequestDto dto) {
        Medico medico = new Medico(dto);
        return medicoRepository.save(medico);
    }

    public Page<MedicoPageDto> listar(Pageable paginacao) {
        Page<Medico> allByAtivoTrue = medicoRepository.findAllByAtivoTrue(paginacao);
        return allByAtivoTrue.map(MedicoPageDto::new);
    }

    @Transactional
    public Medico atualizar(MedicoUpdateDto dto) {
        Medico medico = this.getMedico(dto.id());
        medico.atualizaDados(dto);
        return medico;
    }

    @Transactional
    public void excluir(Long id) {
        Medico medico = this.getMedico(id);
        medico.inativar();
    }

    public Medico detalhar(Long id) {
        return this.getMedico(id);
    }

    private Medico getMedico(Long id) {
        return medicoRepository.findById(id).orElseThrow(() -> new MedicoNotFoundException("Não encontrado medico com ID: " + id));
    }

    public void exists(Long id) {
        if (!medicoRepository.existsById(id))
            throw new MedicoNotFoundException("Não encontrado medico com ID: " + id);
    }

    public Medico escolheMedicoLivre(Especialidade especialidade, LocalDateTime data) {
        return medicoRepository.escolheMedicoPelaEspecialidadeLivreNaData(especialidade, data);
    }
}
