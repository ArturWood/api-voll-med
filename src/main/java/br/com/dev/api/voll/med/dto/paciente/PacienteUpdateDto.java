package br.com.dev.api.voll.med.dto.paciente;

import br.com.dev.api.voll.med.dto.endereco.EnderecoRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PacienteUpdateDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        EnderecoRequestDto endereco
) {
}
