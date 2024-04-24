package br.com.dev.api.voll.med.dto.paciente;

import br.com.dev.api.voll.med.model.endereco.Endereco;
import br.com.dev.api.voll.med.model.paciente.Paciente;

public record PacienteDetailDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public PacienteDetailDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
