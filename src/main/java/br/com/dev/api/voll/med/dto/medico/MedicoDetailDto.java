package br.com.dev.api.voll.med.dto.medico;

import br.com.dev.api.voll.med.model.endereco.Endereco;
import br.com.dev.api.voll.med.model.medico.Especialidade;
import br.com.dev.api.voll.med.model.medico.Medico;

public record MedicoDetailDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
    public MedicoDetailDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
