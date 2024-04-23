package br.com.dev.api.voll.med.dto.medico;

import br.com.dev.api.voll.med.model.medico.Especialidade;
import br.com.dev.api.voll.med.model.medico.Medico;

public record MedicoPageDto(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public MedicoPageDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
