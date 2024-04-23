package br.com.dev.api.voll.med.dto.medico;

import br.com.dev.api.voll.med.dto.endereco.EnderecoRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record MedicoUpdateDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        EnderecoRequestDto endereco
) {
}
