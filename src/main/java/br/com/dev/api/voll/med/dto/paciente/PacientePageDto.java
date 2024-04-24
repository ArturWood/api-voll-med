package br.com.dev.api.voll.med.dto.paciente;

import br.com.dev.api.voll.med.model.paciente.Paciente;

public record PacientePageDto(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public PacientePageDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
